package com.socialmedia.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.socialmedia.model.UserComment;

@Repository 
public interface UserCommentRepository extends MongoRepository<UserComment, String>{
	
	List<UserComment> findByImageId(String imageId);

}
