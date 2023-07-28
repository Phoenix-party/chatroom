package com.example.chat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    // 其他屬性（如發送者、時間戳記等），視需求而定，可以根據需求自行添加。

    // Getters and setters, constructors, etc.
}
