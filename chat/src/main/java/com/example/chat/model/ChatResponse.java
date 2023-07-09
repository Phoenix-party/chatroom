package com.example.chat.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ChatResponse extends BaseResponse {
    ArrayList<ChatEntitly> data;

    public ChatResponse(int code, String message, ArrayList<ChatEntitly> data) {
        super(code, message);

        this.data = data;
    }
}
