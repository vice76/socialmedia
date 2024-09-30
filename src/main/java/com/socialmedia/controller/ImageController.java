package com.socialmedia.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.socialmedia.model.ImageDTO;
import com.socialmedia.model.ImageMetadata;
import com.socialmedia.repository.ImageMetadataRepository;
import com.socialmedia.service.ImageService;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;
    
    @Autowired
    ImageMetadataRepository imageMetadataRepository;

    @Autowired
    private GridFsOperations gridFsOperations;

    @PostMapping("/upload")
    @CrossOrigin
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, 
                                              @RequestParam("desc") String imageDesc,
                                              @RequestParam("userId") int userId, Authentication authentication) throws IOException {
        String imageId = imageService.uploadImage(file, imageDesc, userId);
        return ResponseEntity.ok("Image uploaded successfully with ID: " + imageId);
    }

    
    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<List<ImageDTO>> getAllImages() {
        List<ImageDTO> images = imageService.getAllImageDTOs();
        return ResponseEntity.ok(images);
    }
    
    @GetMapping("/view/{imageId}")
    @CrossOrigin
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String imageId) {
        ImageMetadata metadata = imageMetadataRepository.findById(imageId).orElse(null);
        if (metadata != null) {
            GridFSFile gridFsFile = imageService.getImageFile(metadata.getGridFsId());
            if (gridFsFile != null) {
                GridFsResource resource = gridFsOperations.getResource(gridFsFile);
                try {
                    return ResponseEntity.ok()
                            .contentType(MediaType.parseMediaType(resource.getContentType()))
                            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                            .body(new InputStreamResource(resource.getInputStream()));
                } catch (IOException e) {
                    return ResponseEntity.status(500).build();
                }
            }
        }
        return ResponseEntity.notFound().build();
    }
}

