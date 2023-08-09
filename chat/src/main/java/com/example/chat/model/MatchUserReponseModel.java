package com.example.chat.model;

import lombok.Data;

@Data
public class MatchUserReponseModel extends BaseResponseModel {
    private MatchUserModel data;

    public MatchUserReponseModel(int code, String message
        , MatchUserModel data) {
        super(code, message);

        this.data = data;
    }
}