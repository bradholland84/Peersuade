package com.topdawgapps.peersuade;

import java.util.Date;

/**
 * Created by Brad on 6/24/2015.
 */
public class Discussion {

    private String title;
    private Boolean isRead;
    private String createdOn;

    public Discussion(String title) {
        this.title = title;
        this.createdOn = new Date().toString();
    }

    public Discussion(String title, Date createdOn) {
        this.title = title;
        this.createdOn = createdOn.toString();
    }

    public Discussion(String title, boolean isRead, Date createdOn) {
        this.title = title;
        this.isRead = isRead;
        this.createdOn = createdOn.toString();
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

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn.toString();
    }
}
