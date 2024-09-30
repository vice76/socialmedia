package com.socialmedia.model;

import java.util.List;

public class CommentsDTO {
	
	public CommentsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String imageId;
	
	private List<UserComment> usersComment;

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public List<UserComment> getUsersComment() {
		return usersComment;
	}

	public void setUsersComment(List<UserComment> usersComment) {
		this.usersComment = usersComment;
	}

	public CommentsDTO(String imageId, List<UserComment> usersComment) {
		super();
		this.imageId = imageId;
		this.usersComment = usersComment;
	}

}
