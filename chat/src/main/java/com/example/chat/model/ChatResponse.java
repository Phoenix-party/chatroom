package com.example.chat.model;

import lombok.Data;

@Data
public class ChatResponse extends BaseResponse {
    ChatDetailEntity data;

    public ChatResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
