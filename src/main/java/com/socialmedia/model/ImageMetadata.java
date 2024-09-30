package com.socialmedia.model;

import java.time.Instant;
import java.time.OffsetDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "image_metadata")
public class ImageMetadata {

    @Id
    private String imageId;
    private String imageDesc;
    private String gridFsId;  
    private int userId;
    
    @CreatedDate
    private OffsetDateTime createdAt;

    public ImageMetadata() {}

    public ImageMetadata(String imageId, String imageDesc, String gridFsId, int userId) {
        this.imageId = imageId;
        this.imageDesc = imageDesc;
        this.gridFsId = gridFsId;
        this.userId = userId;
    }

    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	
	// Getters and Setters
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

    public String getGridFsId() {
        return gridFsId;
    }

    public void setGridFsId(String gridFsId) {
        this.gridFsId = gridFsId;
    }
}
