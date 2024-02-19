package com.example.faceapp.entities;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.faceapp.PublicUser;

@Entity
public class Post {
    @PrimaryKey(autoGenerate = true)
    private String author, content;
    private PublicUser publicUser;
    private Uri proPicture;
    private int id;
    private int likes, picture;
    private Uri uriPicture;
    private Comment comment;



//    public Post() {
//        this.picture = R.drawable.gamingsetup;
//    }
    public Post(String author, Uri proPicture, String content, Uri picture, int id) {
        this.author = author;
        this.proPicture= proPicture;
        this.content = content;
        this.picture = 0;
        this.uriPicture = picture;
        this.id = id;
    }
    public Post(String author, Uri proPicture, String content, int picture, int id) {
        this.author = author;
        this.proPicture= proPicture;
        this.content = content;
        this.picture = picture;
        this.uriPicture = null;
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public Uri getUriPicture() {
        return uriPicture;
    }

    public void setUriPicture(Uri uriPicture) {
        this.uriPicture = uriPicture;
    }

    public PublicUser getPublicUser() {
        return publicUser;
    }

    public void setPublicUser(PublicUser publicUser) {
        this.publicUser = publicUser;
    }

    public Uri getProPicture() {
        return proPicture;
    }

    public void setProPicture(Uri proPicture) {
        this.proPicture = proPicture;
    }
}
