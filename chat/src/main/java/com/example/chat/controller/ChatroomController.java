package com.example.chat.controller;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.chat.model.ChatEntitly;
import com.example.chat.model.ChatResponse;

@RestController
public class ChatroomController {
    private static final ArrayList<ChatEntitly> chats = null;

    @RequestMapping(value = "/chats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ChatResponse chats() {
        return getChatList();
    }

    private ChatResponse getChatList() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/shopping?user=root&password=0000");

            stmt = conn.createStatement();

            // ToDo: 改query: select name, category, buy_date, exp_date, quantity from foods
            // f join food_detail fd where f.food_id = fd.id;
            rs = stmt.executeQuery(
                    "select fd.id, name, category, buy_date, exp_date, quantity  from foods f join food_detail fd where f.food_id = fd.id;");

            ArrayList<ChatEntitly> s = new ArrayList<>();
            while (rs.next()) {
                ChatEntitly chatEntitly = new ChatEntitly();
                chatEntitly.setId(rs.getInt("id"));

                chats.add(chatEntitly);
            }

            return new ChatResponse(0, "成功", chats);
        } catch (SQLException e) {
            return new ChatResponse(e.getErrorCode(), e.getMessage(), null);
        } catch (ClassNotFoundException e) {
            return new ChatResponse(1, "無法註冊驅動程式", null);
        }
    }
}
