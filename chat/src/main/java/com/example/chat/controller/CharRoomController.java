package com.example.chat.controller;

import com.example.chat.model.ChatClientModel;
import com.example.chat.model.ServerResponseModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;





@Controller
public class CharRoomController {
    @MessageMapping("/messageControl")
@SendTo("/topic/getResponse")
public ServerResponseModel said(ChatClientModel request) throws InterruptedException {
    Thread.sleep(3000);
    String responseMessage = ":" + request.getMessage();
    return new ServerResponseModel(responseMessage);
}

}

