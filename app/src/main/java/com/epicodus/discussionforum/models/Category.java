package com.epicodus.discussionforum.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 12/5/16.
 */

@Parcel
public class Category {
    String title;
    String image;
    List<Post> posts = new ArrayList<>();

    public Category(){

    }

    public Category(String title, String image, ArrayList<Post> posts) {
        this.title = title;
        this.image = image;
        this.posts = posts;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
