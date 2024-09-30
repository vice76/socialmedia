package com.socialmedia.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNotification(String userId, String commentDetails) {
        messagingTemplate.convertAndSend("/topic/notifications/" + userId, commentDetails);
    }

    @MessageMapping("/notify")
    @SendTo("/topic/notifications")
    public String notifyUser(String message) {
        return message;
    }
}