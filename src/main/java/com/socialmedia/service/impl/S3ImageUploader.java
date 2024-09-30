package com.socialmedia.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.socialmedia.service.ImageUploader;

@Service
public class S3ImageUploader implements ImageUploader {
	
	@Value("${cloud.aws.region.static}")
    private String s3BucketName;
	
    //@Autowired
    private AmazonS3 client;

	@Override
	public String uploadImage(MultipartFile image) {
		
		if(image == null)
		{
			throw new RuntimeException("Image is null");
		}
		
		String actualFileName = image.getOriginalFilename();
		
		String fileName = UUID.randomUUID().toString()+
				actualFileName.substring(actualFileName.lastIndexOf("."));
		ObjectMetadata metaData = new ObjectMetadata();
		metaData.setContentLength(image.getSize());
		
		try {
			PutObjectResult putObjectResult = client.putObject(new PutObjectRequest
					(s3BucketName,fileName , image.getInputStream(),metaData));
			return fileName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<String> allFiles(){
		return null;
	}
	
	@Override
	public String preSignedUrl() {
		return null;
	}
}
