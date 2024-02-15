package com.example.faceapp.entities;

import android.net.Uri;
import android.widget.ImageView;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.faceapp.PublicUser;
import com.example.faceapp.R;
@Entity
public class Post {
    @PrimaryKey(autoGenerate = true)
    private String author, content;
    private PublicUser publicUser;
    private Uri proPicture;
    private int id;
    private int likes;
    private int picture;



    public Post() {
        this.picture = R.drawable.gamingsetup;
    }
    public Post(String author, Uri proPicture, String content, int picture) {
        this.author = author;
        this.proPicture= proPicture;
        this.content = content;
        this.picture = picture;
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
}
