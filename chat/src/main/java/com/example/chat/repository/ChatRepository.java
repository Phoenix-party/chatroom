package com.example.chat.repository;

import com.example.chat.model.ChatMessageModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
















@Repository
public class ChatRepository extends BaseRepository {
    // @Autowired
    // private MyConfig myConfig;
    
    public ArrayList<ChatMessageModel> findAll() {
        super.init();

        if(getConnection() != null) // 確定有連線成功
        {
            try {
                Statement stmt = getConnection().createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM articles");

                ArrayList<ChatMessageModel> data = new ArrayList<>();
            
                // 取得每一筆文章資訊
                while(rs.next()) {
                    ChatMessageModel a = new ChatMessageModel();
                    a.setId(rs.getInt("id"));  // id欄位名稱必須和資料庫蘭為名稱一致
                    a.setRoomId(rs.getInt("roomid"));
                    a.setSenderId(rs.getInt("senderid"));
                    a.setSenderMessage(rs.getString("sendermessage"));
                    a.setSenderTimestamp(rs.getDate("sendertimestamp"));
                    a.setReceiverId(rs.getInt("receiverid"));
                    a.setRceiverMessage(rs.getString("receivermessage"));
                    a.setReceiverTimestamp(rs.getDate("receivertimestamp"));

                    data.add(a);
                }

                // 模擬資料庫斷線
                getConnection().close();
                
                return data;
            } catch (SQLException e) {
                System.out.println("ArticleRepository: " + e.getMessage());
                System.out.println(e.getErrorCode());
                System.out.println(e.getSQLState()); 

                if(e.getErrorCode() == 0 && e.getSQLState().equals("08003")) {
                    closeConnection(); // 斷線並釋放資源
                    init();  // 重新連線
                    return findAll();
                }
                return null;
            }
        }

        return null;
    }

}