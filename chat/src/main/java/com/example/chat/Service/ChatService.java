package com.example.chat.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
public class ChatService {

    private final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();
    private final Queue<String> waitingQueue = new ConcurrentLinkedQueue<>();

    public void registerUserSession(String cookieID, WebSocketSession session) {
        userSessions.put(cookieID, session);
        tryPairing(cookieID);
    }

    private void tryPairing(String cookieID) {
        if (waitingQueue.isEmpty()) {
            waitingQueue.add(cookieID);
        } else {
            String pairedCookieID = waitingQueue.poll();
            if (!pairedCookieID.equals(cookieID)) {
                sendPairingSuccess(cookieID, pairedCookieID);
            } else {
                waitingQueue.add(cookieID); // 如果是同一用户，重新加入队列
            }
        }
    }

    private void sendPairingSuccess(String cookieID1, String cookieID2) {
        WebSocketSession session1 = userSessions.get(cookieID1);
        WebSocketSession session2 = userSessions.get(cookieID2);
    
        String message1 = String.format("{\"type\":\"PAIRING_SUCCESS\", \"pairedWith\":\"%s\"}", cookieID2);
        String message2 = String.format("{\"type\":\"PAIRING_SUCCESS\", \"pairedWith\":\"%s\"}", cookieID1);
    
        try {
            if (session1 != null && session1.isOpen()) {
                session1.sendMessage(new TextMessage(message1));
            }
            if (session2 != null && session2.isOpen()) {
                session2.sendMessage(new TextMessage(message2));
            }
        } catch (IOException e) {
            // 处理 WebSocket 通信错误
        }
    }

    // 其他处理 WebSocket 断开连接等的方法
}

