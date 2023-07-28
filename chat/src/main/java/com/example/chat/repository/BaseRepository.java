package com.example.chat.repository;

import com.example.chat.model.MyConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;





@Repository
public class BaseRepository {
    @Autowired
    private MyConfig myConfig;

    private Connection conn = null;

    public void init() {
        // 如果已經連線過，就不需要再連線
        if(this.conn != null) return;

        try {
            // 註冊驅動程式
            Class.forName(myConfig.getDriverClassName());

            // 建立連線
            conn = DriverManager.getConnection(myConfig.getUrl() + "?allowPublicKeyRetrieval=true&useSSL=false&user=" + 
                myConfig.getUsername() + "&password=" + myConfig.getPassword());

        } catch(SQLException e) {
            conn = null;
            System.out.println("BaseRespsition: " + e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
        } catch(ClassNotFoundException e) {
            conn = null;
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    // 關閉練線並清除連線物件
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        conn = null;
    }
}