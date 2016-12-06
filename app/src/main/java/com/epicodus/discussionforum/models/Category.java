package com.epicodus.discussionforum.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/5/16.
 */

@Parcel
public class Category {
    String title;
    String image;

    public Category(){

    }

    public Category(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }




}
