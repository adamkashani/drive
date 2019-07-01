package core.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import core.exception.DriveException;
import core.logic.FileService;

@RestController
@RequestMapping(name = "/file")
public class FileApi {

	private FileService fileService;

	@Autowired
	public FileApi(FileService fileService) {
		super();
		this.fileService = fileService;
	}

//	@PostMapping(produces = MediaType..mul)
	@PostMapping
	@RequestMapping("/uplode")
	public String uplodeFile(@CookieValue(name = "token") long tokenId, MultipartFile file) throws DriveException {
		fileService.uplodeFile(file, tokenId);
		return "redirect:/";
	}
	
	@GetMapping
	@RequestMapping("/download/fileName")
	public Resource downloadFile(@CookieValue(name = "token") long tokenId , @RequestParam() String fileName) throws DriveException {
		return fileService.downloadFile(tokenId , fileName);
	}

}
