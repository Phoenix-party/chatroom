package com.example.chat.Service;

import com.example.chat.model.MyConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class RandomChatRoomService {

    @Autowired
    private MyConfig myConfig;

    // 隨機配對功能
    public void matchUsers() {
        List<Integer> unmatchedUsers = new ArrayList<>();
        Connection connection = null;

        try {
            connection = myConfig.getConnection();
            if (connection == null) {
                System.out.println("無法獲取資料庫連線");
                return;
            }

            // 查詢未被匹配的用戶ID
            String query = "SELECT id FROM users WHERE matched = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                unmatchedUsers.add(userId);
            }

            // 如果找到至少兩個未被匹配的用戶ID，進行配對
            if (unmatchedUsers.size() >= 2) {
                Random random = new Random();
                int index1 = random.nextInt(unmatchedUsers.size());
                int index2;
                do {
                    index2 = random.nextInt(unmatchedUsers.size());
                } while (index1 == index2);

                int user1Id = unmatchedUsers.get(index1);
                int user2Id = unmatchedUsers.get(index2);

                // 更新資料庫中兩個用戶的matched狀態為已匹配
                String updateQuery = "UPDATE users SET matched = 1 WHERE id IN (?, ?)";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setInt(1, user1Id);
                updateStatement.setInt(2, user2Id);
                updateStatement.executeUpdate();

                // 在這裡執行將用戶進行匿名聊天的相關邏輯
                System.out.println("用戶 " + user1Id + " 和用戶 " + user2Id + " 成功匹配，可以進行匿名聊天！");
            } else {
                System.out.println("目前找不到足夠的用戶進行配對，請稍後再試。");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

