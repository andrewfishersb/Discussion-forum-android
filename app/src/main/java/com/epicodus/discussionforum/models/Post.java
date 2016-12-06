package com.epicodus.discussionforum.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/6/16.
 */
@Parcel
public class Post {
    String title;
    String details;
    private String pushId;

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

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
