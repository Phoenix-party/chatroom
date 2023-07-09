package com.example.chat.model;

import lombok.Data;

@Data
public class BaseResponse {
    protected int code;
    protected String message;

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}