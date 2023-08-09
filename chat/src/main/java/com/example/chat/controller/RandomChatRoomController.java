package com.example.chat.controller;

import com.example.chat.Service.RandomChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class RandomChatRoomController {
    @Autowired
    private RandomChatRoomService randomChatRoomService;

    @PostMapping("/chat/match")
    @CrossOrigin(value = "*") // 允許跨域請求
public ResponseEntity<String> matchUsers(@RequestParam String cookieID, @RequestParam String webSocketID) {
    randomChatRoomService.matchUsers(cookieID, webSocketID);
    return ResponseEntity.ok("已發起配對請求，請等待匹配結果。");
}

    @PostMapping("/chat/disconnect")
    @CrossOrigin(value = "*")
public ResponseEntity<String> disconnectUser(@RequestParam String cookieID) {
    randomChatRoomService.removeMatchedUser(cookieID);
    return ResponseEntity.ok("已斷開連接，資料已刪除。");
}
}

