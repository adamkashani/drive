package core.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.logic.FileService;

@RestController
@RequestMapping(name = "file")
public class FileApi {

	private FileService fileService;

	@Autowired
	public FileApi(FileService fileService) {
		super();
		this.fileService = fileService;
	}

//	public String uplodeFile(@CookieValue(name = "token")String token , ) {
//		
//	}

}
