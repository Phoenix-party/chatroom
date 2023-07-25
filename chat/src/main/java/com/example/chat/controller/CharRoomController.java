package com.example.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class CharRoomController {
    @MessageMapping("/messageControl")
    @SendTo("/topic/getResponse")
    public String handleChatMessage(String message) throws InterruptedException {
        // 模擬處理訊息的時間（這裡設置為3秒）
        Thread.sleep(3000);
        String responseMessage = ":" + message;
        return responseMessage;
    }
}
