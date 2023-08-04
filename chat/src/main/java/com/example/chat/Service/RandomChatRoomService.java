package com.example.chat.Service;

import com.example.chat.model.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class RandomChatRoomService {

    @Autowired
    private MyConfig myConfig;

    // 隨機配對功能
    public void matchUsers(String cookieID, String webSocketID) {
        // 將使用者的 WebSocket ID 和 Cookie ID 關聯存入資料庫的 user_connections 表
        addUserConnection(cookieID, webSocketID);
    
        // 查詢資料庫，尋找與目前使用者不同且未被匹配的使用者
        String matchedWebSocketID = getMatchedWebSocketID(cookieID);
    
        if (matchedWebSocketID != null) {
            // 找到匹配的使用者，建立連接，並刪除配對資料
            addUserConnection(matchedWebSocketID, webSocketID);
            removeMatchedUser(matchedWebSocketID);
            removeMatchedUser(cookieID);
    
            // 在這裡執行將用戶進行匿名聊天的相關邏輯
            System.out.println("用戶 " + cookieID + " 和用戶 " + matchedWebSocketID + " 成功匹配，可以進行匿名聊天！");
        } else {
            System.out.println("目前找不到足夠的用戶進行配對，請稍後再試。");
        }
    }
    
    private void addUserConnection(String cookieID, String webSocketID) {
        // 在這裡執行將 cookieID 和 webSocketID 儲存到 user_connections 資料表的邏輯
    }
    
    private String getMatchedWebSocketID(String cookieID) {
        // 在這裡執行查詢 user_connections 資料表的邏輯，找到匹配的使用者的 WebSocket ID
        return null;
    }
    
    public void removeMatchedUser(String cookieID) {
        // 在這裡執行刪除 user_connections 資料表中特定 cookieID 的資料的邏輯
    }
}

