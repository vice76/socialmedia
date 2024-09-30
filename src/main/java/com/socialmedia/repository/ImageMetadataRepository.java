package com.socialmedia.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.socialmedia.model.ImageMetadata;

@Repository
public interface ImageMetadataRepository extends MongoRepository<ImageMetadata, String>{

	List<ImageMetadata> findByUserId(int userId);

}
