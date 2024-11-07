package com.example.chat.model;

public class MatchNotification {
    private String cookieID;
    private String webSocketID;

    // 构造器
    public MatchNotification(String cookieID, String webSocketID) {
        this.cookieID = cookieID;
        this.webSocketID = webSocketID;
    }

    // Getter和Setter
    public String getCookieID() {
        return cookieID;
    }

    public void setCookieID(String cookieID) {
        this.cookieID = cookieID;
    }

    public String getWebSocketID() {
        return webSocketID;
    }

    public void setWebSocketID(String webSocketID) {
        this.webSocketID = webSocketID;
    }
}
