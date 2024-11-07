package com.example.chat.controller;

import com.example.chat.Service.RandomChatRoomService;
import com.example.chat.controller.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RandomChatRoomController {

    @Autowired
    private RandomChatRoomService randomChatRoomService;

    private Map<String, ChatRoom> chatRooms = new HashMap<>();

    @PostMapping("/chat/match")
    @CrossOrigin(value = "*")
    public ResponseEntity<String> matchUsers(@RequestParam String cookieID) {
        String webSocketID = randomChatRoomService.matchUsers(cookieID);

        if (webSocketID != null) {
            ChatRoom chatRoom = new ChatRoom(webSocketID);
            chatRooms.put(webSocketID, chatRoom);
            return ResponseEntity.ok(webSocketID);
        } else {
            return ResponseEntity.badRequest().body("無法發起配對請求，請稍後再試。");
        }
    }

    @PostMapping("/chat/disconnect")
    @CrossOrigin(value = "*")
    public ResponseEntity<String> disconnectUser(@RequestParam String cookieID) {
        String webSocketID = randomChatRoomService.findWebSocketIdByCookieId(cookieID);

        if (webSocketID != null) {
            randomChatRoomService.removeMatchedUsers(cookieID, webSocketID);
            return ResponseEntity.ok("已斷開連接，資料已刪除。");
        } else {
            return ResponseEntity.badRequest().body("找不到匹配的WebSocket ID，無法斷開連接。");
        }
    }
}
