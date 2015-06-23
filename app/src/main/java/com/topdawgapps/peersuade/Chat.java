package com.topdawgapps.peersuade;

/**
 * Created by Brad on 6/23/2015.
 */
public class Chat {
    private String author;
    private String message;

    @SuppressWarnings("unused")
    public Chat() {
        //default empty constructor for Firebase
    }

    Chat(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}
