package com.topdawgapps.peersuade;

import java.util.Date;

/**
 * Created by Brad on 7/4/2015.
 */
public class Topic {

    private String title;
    private String URL;
    private Date date;

    public Topic(String title, String URL, Date date) {
        this.title = title;
        this.URL = URL;
        this.date = date;
    }

    @SuppressWarnings("unused")
    public Topic () {
        //required default empty constructor for Firebase
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
