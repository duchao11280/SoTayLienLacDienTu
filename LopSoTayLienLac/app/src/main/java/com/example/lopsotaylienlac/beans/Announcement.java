package com.example.lopsotaylienlac.beans;

import java.util.Date;

public class Announcement {
    private int announID;
    private String title;
    private String announContent;
    private String senderName;
    private boolean isRead;
    private Date dateSend;
    private String receiverName;

    public Announcement(int announID, String title, String announContent, String senderName, boolean isRead, Date dateSend, String receiverName) {
        this.announID = announID;
        this.title = title;
        this.announContent = announContent;
        this.senderName = senderName;
        this.isRead = isRead;
        this.dateSend = dateSend;
        this.receiverName = receiverName;
    }

    public Announcement(int announID, String title, String announContent, String senderName, Date dateSend, String receiverName) {
        this.announID = announID;
        this.title = title;
        this.announContent = announContent;
        this.senderName = senderName;
        this.dateSend = dateSend;
        this.receiverName = receiverName;
    }

    public int getAnnounID() {
        return announID;
    }

    public void setAnnounID(int announID) {
        this.announID = announID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnounContent() {
        return announContent;
    }

    public void setAnnounContent(String announContent) {
        this.announContent = announContent;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Date getDateSend() {
        return dateSend;
    }

    public void setDateSend(Date dateSend) {
        this.dateSend = dateSend;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}
