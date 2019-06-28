package core.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource(value = "classpath:file.properties")
public class PropertiesFile {
	
	@Value("${root}")
	private String root ; 
	
	@PostConstruct
	public void print() {
		System.out.println("the root is : "+root);
	}

	public String getRootPath() {
		return root;
	}

	
	
	
	

}
