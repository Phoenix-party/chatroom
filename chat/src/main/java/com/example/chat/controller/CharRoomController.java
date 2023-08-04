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
    @CrossOrigin(value = "*") // 允許跨域請求
    public ServerResponseModel said(ChatClientModel request) throws InterruptedException {
        Thread.sleep(3000);
        String responseMessage = request.getMessage();
        saveChatRecord(request.getSenderCookieID(), request.getReceiverCookieID(), responseMessage);
        return new ServerResponseModel(responseMessage);
    }
    
    private void saveChatRecord(String senderCookieID, String receiverCookieID, String content) {
        // 在這裡執行將聊天記錄儲存到 chat_records 資料表的邏輯
    }

    @MessageMapping("/connect")
    @SendTo("/topic/getResponse")
    @CrossOrigin(value = "*") // 允許跨域請求
    public ServerResponseModel handleConnect(ChatClientModel request) {
        // 在這裡處理接收到的 /app/connect 訊息
        String cookieID = request.getCookieID();
        String webSocketID = request.getWebSocketID();

        // 在這裡可以將 cookieID 和 webSocketID 關聯起來，然後進行配對等操作

        // 返回一個回應給前端
        return new ServerResponseModel("Connected successfully!");
    }
}

