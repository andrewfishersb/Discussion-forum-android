package com.epicodus.discussionforum.models;

/**
 * Created by Guest on 12/6/16.
 */
public class Post {
    String title;
    String details;

    public Post() {}

    public Post(String title, String details) {
        this.title = title;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public String getTitle() {
        return title;
    }
}
