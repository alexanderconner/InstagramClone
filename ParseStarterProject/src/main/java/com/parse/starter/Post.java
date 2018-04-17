package com.parse.starter;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander.conner on 4/13/2018.
 *
 * A Post is created by a user. It consists of an image, its comments, and other data.
 *
 */

public class Post {

    private String imageID;
    private Bitmap image;
    private String authorID; //User ID
    private String author; //Username
    private ArrayList<String> comments;
    private String dateCreated;

    public Post(String imageID, Bitmap image, String authorID, String author, ArrayList<String> comments, String dateCreated) {
        this.imageID = imageID;
        this.image = image;
        this.authorID = authorID;
        this.author = author;
        this.comments = comments;
        this.dateCreated = dateCreated;
    }

    public String getImageID() {
        return imageID;
    }

    public Bitmap getImage() { return image; }

    public String getAuthorID() {
        return authorID;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getComments() {
        return comments;
    }

    public String getDateCreated() {
        return dateCreated;
    }
}
