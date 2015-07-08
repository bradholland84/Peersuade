package com.topdawgapps.peersuade;

import java.util.Date;

/**
 * Created by Brad on 6/24/2015.
 */
public class Discussion {

    private String title;
    private Boolean isRead;
    private String createdOn;
    private String user1;
    private String user2;

    public Discussion(String title, String user1, String user2) {
        this.title = title;
        this.createdOn = new Date().toString();
        this.user1 = user1;
        this.user2 = user2;
    }


    public Discussion(String title, String createdOn) {
        this.title = title;
        this.createdOn = createdOn;
    }

    public Discussion(String title, boolean isRead, String createdOn) {
        this.title = title;
        this.isRead = isRead;
        this.createdOn = createdOn;
    }

    @SuppressWarnings("unused")
    public Discussion() {
        //default empty constructor for Firebase
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }
}
