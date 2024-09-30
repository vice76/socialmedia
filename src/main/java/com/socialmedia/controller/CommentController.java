package com.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia.model.CommentsDTO;
import com.socialmedia.model.Response;
import com.socialmedia.model.UserComment;
import com.socialmedia.service.UserCommentService;

//import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/user/post")
public class CommentController {
	
	@Autowired
	private UserCommentService userCommentService;
	
    @Autowired
    private NotificationController notificationController;
	
	
	@PostMapping("/comment")
	@CrossOrigin
	public ResponseEntity<UserComment> postComment(@RequestBody UserComment comment){
		UserComment postComment = userCommentService.postComment(comment);
		Response res = new Response();
		
		int userId = postComment.getUserId();
        String commentDetails = "User " + postComment.getCommentedUserId() + " commented on your photo: " + postComment.getComment();
		notificationController.sendNotification(String.valueOf(userId), commentDetails);
		if(postComment != null) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.header("Commented successfully")
				.body(postComment);
		}
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.header("Something went wrong")
				.body(postComment);
		
	}
	
	@GetMapping("/viewComment/{imageId}")
	@CrossOrigin
	public ResponseEntity<CommentsDTO> getCommentByImageId(@PathVariable String imageId){
		CommentsDTO comments = userCommentService.getAllCommentByImageId(imageId);
		if(comments != null) {
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.header("comments")
					.body(comments);
		}
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.header("No Comments Found!")
				.body(null);
	}

}
