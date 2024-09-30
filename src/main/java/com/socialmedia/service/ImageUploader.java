package com.socialmedia.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {
	
	String uploadImage(MultipartFile image);
	
	List<String> allFiles();
	
	String preSignedUrl();
}
