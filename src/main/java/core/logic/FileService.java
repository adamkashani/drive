package core.logic;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.dao.IFileDao;

@Service
public class FileService {

	private IFileDao fileDao;

	@Autowired
	public FileService(IFileDao fileDao) {
		super();
		this.fileDao = fileDao;
	}
	
	@PostConstruct
	public void print() {
		System.out.println(fileDao);
	}
	
	
	
	
	
}
