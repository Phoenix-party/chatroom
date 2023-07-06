package com.example.chat.model;

import lombok.Data;

@Data
public class ChatDetailResponse extends BaseResponse {
    ChatDetailEntity data;

    public ChatDetailResponse(int code, String message, ChatDetailEntity data) {
        super(code, message);

        this.data = data;
    }

}
