package com.example.chat.repository;

import com.example.chat.model.MatchUserModel;
import com.example.chat.model.MyConfig;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;







@Repository
public class ChatRepository extends BaseRepository {
     @Autowired
     private MyConfig myConfig;
    
    public ArrayList<MatchUserModel> findAll() {
        super.init();

        if(getConnection() != null) // 確定有連線成功
        {
            try {
                Statement stmt = getConnection().createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM user_connections");

                ArrayList<MatchUserModel> data = new ArrayList<>();
            
                
                while(rs.next()) {
                    MatchUserModel a = new MatchUserModel();
                    a.setId(rs.getInt("id"));  // id欄位名稱必須和資料庫蘭為名稱一致
                    a.setCookieId(rs.getString("cookie_id"));
                    a.setWebsocketId(rs.getString("websocket_id"));
                    
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