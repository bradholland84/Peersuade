package com.topdawgapps.peersuade;

/**
 * Created by Brad on 6/24/2015.
 */
public class Discussion {

    private String title;
    private Boolean isRead;

    public Discussion(String title, boolean isRead) {
        this.title = title;
        this.isRead = isRead;
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
}
