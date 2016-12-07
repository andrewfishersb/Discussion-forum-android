package com.epicodus.discussionforum.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/6/16.
 */
@Parcel
public class Post {
    String title;
    String details;
    private String categoryId;

    public Post() {}

    public Post(String title, String details, String categoryId) {
        this.title = title;
        this.details = details;
        this.categoryId = categoryId;
    }

    public String getDetails() {
        return details;
    }

    public String getTitle() {
        return title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setPushId(String pushId) {
        this.categoryId = pushId;
    }
}
