package com.socialmedia.model;

import java.time.Instant;
import java.time.OffsetDateTime;

public class ImageDTO {
    private String imageId;
    private String imageDesc;
    private int userId;
    private String imageUrl; 
    private OffsetDateTime createdAt;
    private String userName;

    public ImageDTO() {}

    public ImageDTO(String imageId, String imageDesc, int userId, String imageUrl,   OffsetDateTime createdAt, String userName) {
        this.imageId = imageId;
        this.imageDesc = imageDesc;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.createdAt=createdAt;
        this.userName=userName;
    }

    // Getters and Setters

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
       this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

