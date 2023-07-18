package com.example.chat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MatchController {

    private List<String> availableIds = new ArrayList<>(); // 可用的 ID 列表

    @PostMapping("/match")
    public ResponseEntity<String> match() {
        String id = UUID.randomUUID().toString(); // 生成一個唯一的 ID
        availableIds.add(id); // 將 ID 加入可用的 ID 列表

        if (availableIds.size() >= 2) {
            String matchedId = availableIds.remove(0); // 從可用的 ID 列表中移除第一個 ID
            return ResponseEntity.ok(matchedId);
        } else {
            return ResponseEntity.accepted().build(); // 等待配對
        }
    }
}
