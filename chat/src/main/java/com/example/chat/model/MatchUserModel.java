package com.example.chat.model;

import lombok.Data;

@Data
public class MatchUserModel {
    private int Id;
    private String CookieId;
    private String WebsocketId;

    public int getId() {
        return Id;
    }
  
    public void setId(int Id) {
        this.Id = Id;
    }
  
    public String getCookieId() {
        return CookieId;
    }
  
    public void setCookidId(String CookidId) {
        this.CookieId = CookidId;
    }
  
    public String getWebsocketId() {
        return WebsocketId;
    }
  
    public void setWebsocketId(String WebsocketId) {
        this.WebsocketId = WebsocketId;
    }
    
}
