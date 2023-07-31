package com.example.chat.controller;

import com.example.chat.model.ChatClientModel;
import com.example.chat.model.ServerResponseModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;


@Controller
public class CharRoomController {
@MessageMapping("/messageControl")
@SendTo("/topic/getResponse")
@CrossOrigin(value = "*") //允許跨域請求
public ServerResponseModel said(ChatClientModel request) throws InterruptedException {
    Thread.sleep(3000);
    String responseMessage =  request.getMessage();
    return new ServerResponseModel(responseMessage);
}

}
