package com.socialmedia.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.socialmedia.model.ImageDTO;
import com.socialmedia.model.ImageMetadata;
import com.socialmedia.model.User;
import com.socialmedia.repository.ImageMetadataRepository;
import com.socialmedia.repository.UserRepository;

@Service
public class ImageService {

    @Autowired
    private GridFsTemplate gridFsTemplate;
    
    @Autowired
	private UserRepository userRepository;

    @Autowired
    private ImageMetadataRepository imageMetadataRepository;

    public String uploadImage(MultipartFile file, String imageDesc, int userId) throws IOException {
        ObjectId gridFsId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());

        ImageMetadata imageMetadata = new ImageMetadata(gridFsId.toHexString(), imageDesc, gridFsId.toHexString(), userId);
        imageMetadataRepository.save(imageMetadata);

        return gridFsId.toHexString();  // Return the stored image's ID
    }

    
    public List<GridFSFile> getALLImageFile() {
        List<ImageMetadata> imageMetadata = imageMetadataRepository.findAll();
        
        List<GridFSFile> res = new ArrayList<>();
        if (imageMetadata != null) {
        	for(ImageMetadata im : imageMetadata) {
        		res.add( gridFsTemplate.findOne(new
        				  org.springframework.data.mongodb.core.query.Query(
        				  org.springframework.data.mongodb.core.query.Criteria.where("_id").is(
        						  im.getGridFsId()))));
        	}
        	
        	
        }
        return res;
    }
    
    
    public List<ImageDTO> getAllImageDTOs() {
        List<ImageMetadata> metadataList = imageMetadataRepository.findAll();

        return metadataList.stream().map(metadata -> {
        	User user = userRepository.findById(metadata.getUserId());
            String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/images/view/")
                    .path(metadata.getImageId())
                    .toUriString();
            return new ImageDTO(
                    metadata.getImageId(),
                    metadata.getImageDesc(),
                    metadata.getUserId(),
                    imageUrl,
                    metadata.getCreatedAt(),
                    user.getUserName()
            );
        }).collect(Collectors.toList());
    }
    
    public List<ImageMetadata> getImagesByUserId(int userId) {
        return imageMetadataRepository.findByUserId(userId);
    }

    public GridFSFile getImageFile(String gridFsId) {
        return gridFsTemplate.findOne(
                new org.springframework.data.mongodb.core.query.Query(
                        org.springframework.data.mongodb.core.query.Criteria.where("_id").is(gridFsId)));
    }
}

