package core.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import core.dao.IFileDao;
import core.entity.FileEntity;
import core.entity.UserEntity;
import core.exception.DriveException;
import core.exception.ErrorType;

@Service
@PropertySource("file:file.properties")
public class FileService {

	final private static String PROPERTIES_FILE = "file.properties";
	// path to root folder of this app (to save all client files)
	@Value("${root}")
	private String rootPath;
	private Properties properties = new Properties();

	CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES };

	@Autowired
	private IFileDao fileDao;
	@Autowired
	private UserService userService;

	public FileService(IFileDao fileDao, UserService userService) {
		super();
		this.fileDao = fileDao;
		this.userService = userService;
		this.rootPath = getValueProperties("root");
	}

	public FileService() {
		super();
//		this.rootPath = getValueProperties("root");
	}

	@PostConstruct
	public void print() {
		System.out.println(fileDao);

	}

	public void uploadFile(MultipartFile file, String userName) {
		File copy = new File(rootPath + "/" + userName + "/" + file.getOriginalFilename());
		Path path = copy.toPath();
		try {
			Files.copy(file.getInputStream(), path, options);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("faild uploud file ");
			e.printStackTrace();
		}
	}

	public Resource download(String fileName, long userId) throws Exception {

		if (canDownload(fileName, userId)) {
			FileEntity file = fileDao.findByFileName(fileName);
			try {
				return new InputStreamResource(new FileInputStream(file.getPathToFile()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new DriveException(ErrorType.FILE_NOT_EXISTS_ON_SET_FILES);
		}
		// Just in case it is thrown Exception from FileNotFoundException(never we check
		// if the file exists in canDownload)
		return null;
	}

	/**
	 * this method create directory of new user , in this directory we will save all
	 * this client want to upload
	 * 
	 * @param name
	 */
	public void createDirectoryUser(String name) {

		boolean createResult = new File(rootPath + "/" + name).mkdirs();

		if (createResult == true) {
			return;
		} else {
			// TODO throw new exception for faild create dir for new user
		}

	}

	private String getValueProperties(String ket) {
		try (InputStream inputStream = new FileInputStream(PROPERTIES_FILE);) {
			properties.load(inputStream);
			return properties.getProperty(ket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to read from PROPERTIES_FILE");
		}
		return null;
	}

	private boolean canDownload(String fileName, long userId) {
		UserEntity user = userService.get(userId);
		for (FileEntity file : user.getFileEntities()) {
			if (file.getFileName().equals(fileName)) {
				return true;
			}
		}
		return false;

		// TODO Auto-generated method stub

	}

	public void removeByOwnerName(String owner) {
		fileDao.deleteByOwner(owner);
	}

}
