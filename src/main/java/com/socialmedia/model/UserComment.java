package com.socialmedia.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usersComments")
public class UserComment {
	
	private int userId;
	
	private String comment;
	
	private String imageId;
	
	private int commentedUserId;
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public int getCommentedUserId() {
		return commentedUserId;
	}

	public void setCommentedUserId(int commentedUserId) {
		this.commentedUserId = commentedUserId;
	}

	public UserComment(int userId, String comment, String imageId, int commentedUserId) {
		super();
		this.userId = userId;
		this.comment = comment;
		this.imageId = imageId;
		this.commentedUserId = commentedUserId;
	}
	
	

}
