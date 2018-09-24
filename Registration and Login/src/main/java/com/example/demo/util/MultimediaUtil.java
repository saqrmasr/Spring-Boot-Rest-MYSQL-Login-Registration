package com.example.demo.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class MultimediaUtil {
	private static final String IMAGES_PATH = "media"; 
	//private static final String VIDEOS_PATH = "media/videos"; 
	private static final String BASE_PATH = "/src/main/resources/static/";
	
	public static String uploadImages(MultipartFile file) {
		String imagePath = null;
		try {
			File imageFile = new File(BASE_PATH+IMAGES_PATH);
			
			
			if(!imageFile.exists())
				imageFile.mkdirs();
				 
			byte[] bytes = file.getBytes(); 
			
			imagePath = BASE_PATH + IMAGES_PATH +"/"+ file.getOriginalFilename();
			
			Path path = Paths.get(imagePath);
			Files.write(path, bytes); 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return imagePath;
	}
}
