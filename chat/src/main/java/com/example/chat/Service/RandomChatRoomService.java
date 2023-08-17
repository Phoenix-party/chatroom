package com.example.chat.Service;

import com.example.chat.controller.ChatRoom;
import com.example.chat.model.MyConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;







@Service
public class RandomChatRoomService {
    
    private Map<String, ChatRoom> chatRooms = new HashMap<>();
    private Map<String, Object> matchLocks = new HashMap<>();

    @Autowired
    private MyConfig myConfig; // 注入 MyConfig

    // 生成隨機 WebSocket ID
    public String generateWebSocketId() {
        return UUID.randomUUID().toString();
    }

    // 隨機配對功能
    public String matchUsers(String cookieID) {
        String webSocketID = generateWebSocketId();

        // 將使用者的 WebSocket ID 和 Cookie ID 關聯存入資料庫的 user_connections 表
        addUserConnection(cookieID, webSocketID);

        // 查詢資料庫，尋找與目前使用者不同且未被匹配的使用者
        String matchedWebSocketID = getMatchedWebSocketID(cookieID);

        if (matchedWebSocketID != null) {
            // 找到匹配的使用者，建立連接，創建聊天室並維護連接列表
            addUserConnection(matchedWebSocketID, webSocketID);
            removeMatchedUser(matchedWebSocketID);
            removeMatchedUser(cookieID);

            // 在這裡進行將使用者進行匿名聊天的相關邏輯
            ChatRoom chatRoom = new ChatRoom(webSocketID);
            chatRooms.put(webSocketID, chatRoom); // 將聊天室加入chatRooms列表中

            System.out.println("使用者 " + cookieID + " 和使用者 " + matchedWebSocketID + " 成功匹配，可以進行匿名聊天！");

            // 通知等待中的线程
            synchronized (matchLocks.get(matchedWebSocketID)) {
                matchLocks.get(matchedWebSocketID).notify();
            }

            return webSocketID; // 回傳生成的 WebSocket ID
        } else {
            System.out.println("目前找不到足夠的使用者進行配對，請稍後再試。");

            // 创建锁对象，用于等待匹配成功
            Object lock = new Object();
            matchLocks.put(cookieID, lock);

            // 等待匹配成功
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 匹配成功后继续处理
            return webSocketID;
        }
    }

    private void addUserConnection(String cookieID, String webSocketID) {
        try (Connection connection = myConfig.getConnection()) {
            String sql = "INSERT INTO user_connections (cookie_id, websocket_id) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, cookieID);
                preparedStatement.setString(2, webSocketID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getMatchedWebSocketID(String cookieID) {
        try (Connection connection = myConfig.getConnection()) {
            String sql = "SELECT websocket_id FROM user_connections WHERE cookie_id <> ? AND websocket_id IS NULL LIMIT 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, cookieID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("websocket_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeMatchedUser(String cookieID) {
        try (Connection connection = myConfig.getConnection()) {
            String sql = "DELETE FROM user_connections WHERE cookie_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, cookieID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
