package com.example.chat.model;

import lombok.Data;

@Data
public class BaseResponseModel {
    protected int code;
    protected String message;

    public BaseResponseModel(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
