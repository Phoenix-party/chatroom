package com.example.chat.controller;

import com.example.chat.Service.RandomChatRoomService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;



@RestController
public class RandomChatRoomController {

    @Autowired
    private RandomChatRoomService randomChatRoomService;

    private Map<String, ChatRoom> chatRooms = new HashMap<>();

    @PostMapping("/chat/match")
    @CrossOrigin(value = "*")
    public ResponseEntity<String> matchUsers(@RequestParam String cookieID, WebSocketSession session) {
        String webSocketID = randomChatRoomService.matchUsers(cookieID, session);

        if (webSocketID != null) {
            ChatRoom chatRoom = new ChatRoom(webSocketID);
            chatRooms.put(webSocketID, chatRoom);
            return ResponseEntity.ok("已發起配對請求，請等待匹配結果。WebSocket ID：" + webSocketID);
        } else {
            return ResponseEntity.badRequest().body("無法發起配對請求，請稍後再試。");
        }
    }

    @PostMapping("/chat/disconnect")
    @CrossOrigin(value = "*")
    public ResponseEntity<String> disconnectUser(@RequestParam String cookieID) {
        randomChatRoomService.removeMatchedUser(cookieID);
        return ResponseEntity.ok("已斷開連接，資料已刪除。");
    }
}


