package com.example.chat.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.socket.WebSocketSession;


public class ChatRoom {
    private String roomId;
    private List<WebSocketSession> sessions = new ArrayList<>();

    public ChatRoom(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }

    public List<WebSocketSession> getSessions() {
        return sessions;
    }
}
