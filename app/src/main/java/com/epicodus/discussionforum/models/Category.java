package com.epicodus.discussionforum.models;

/**
 * Created by Guest on 12/5/16.
 */
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