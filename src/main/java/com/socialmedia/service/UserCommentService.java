package com.socialmedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia.model.CommentsDTO;
import com.socialmedia.model.User;
import com.socialmedia.model.UserComment;
import com.socialmedia.repository.UserCommentRepository;
import com.socialmedia.repository.UserRepository;

@Service
public class UserCommentService {
	
	@Autowired
	private UserCommentRepository userCommentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public UserComment postComment(UserComment comment) {
		if(comment != null) {
			return userCommentRepository.save(comment);
		}
		return null;
	}
	
	public CommentsDTO getAllCommentByImageId(String imageId){
		List<UserComment> users = userCommentRepository.findByImageId(imageId);
		if(users != null) {
			for(UserComment user : users) {
				User userDetails = userRepository.findById(user.getCommentedUserId());
				user.setUserName(userDetails.getUserName());
			}
			CommentsDTO comments = new CommentsDTO(imageId, users);
			return comments;
		}
		return null;
		
		
	}

}
