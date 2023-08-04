package com.example.chat.model;

public class ChatClientModel {
    private String message;
    private String cookieID;
    private String webSocketID;
    private String senderCookieID;
    private String receiverCookieID;

    public String getSenderCookieID() {
        return senderCookieID;
    }

    public void setSenderCookieID(String senderCookieID) {
        this.senderCookieID = senderCookieID;
    }

    public String getReceiverCookieID() {
        return receiverCookieID;
    }

    public void setReceiverCookieID(String receiverCookieID) {
        this.receiverCookieID = receiverCookieID;
    }

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
    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


