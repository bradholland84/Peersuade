package com.topdawgapps.peersuade;

/**
 * Created by Brad on 7/4/2015.
 */
public class Topic {

    private String title;
    private String URL;
    private String date;

    public Topic(String title, String URL, String date) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
