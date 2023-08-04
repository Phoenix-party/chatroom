package com.example.chat.model;

import java.util.Date;

public class ChatMessageModel {
    private int Id;
    private int RoomId;
    private int SenderId;
    private String SenderMessage;
    private Date SenderTimestamp;
    private int ReceiverId;
    private String ReceiverMessage;
    private Date ReceiverTimestamp;

    // 省略了構造函數

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int RoomId) {
        this.RoomId = RoomId;
    }

    public int getSenderId() {
        return SenderId;
    }

    public void setSenderId(int SenderId) {
        this.SenderId = SenderId;
    }

    public String getSenderMessage() {
        return SenderMessage;
    }

    public void setSenderMessage(String SenderMessage) {
        this.SenderMessage = SenderMessage;
    }

    public Date getSenderTimestamp() {
        return SenderTimestamp;
    }

    public void setSenderTimestamp(Date SenderTimestamp) {
        this.SenderTimestamp = SenderTimestamp;
    }

    public int getReceiverId() {
        return ReceiverId;
    }

    public void setReceiverId(int ReceiverId) {
        this.ReceiverId = ReceiverId;
    }

    public String getReceiverMessage() {
        return ReceiverMessage;
    }

    public void setReceiverMessage(String ReceiverMessage) {
        this.ReceiverMessage = ReceiverMessage;
    }

    public Date getReceiverTimestamp() {
        return ReceiverTimestamp;
    }

    public void setReceiverTimestamp(Date ReceiverTimestamp) {
        this.ReceiverTimestamp = ReceiverTimestamp;
    }
}
