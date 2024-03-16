package com.example.faceapp.model;

import java.sql.Timestamp;

public class Comment {
    private String comment;
    private PublicUser commenter;
    private Timestamp time;
    private int id, numLikes;
    private boolean liked;

    public Comment(String comment, PublicUser commenter, Timestamp time, int id) {
        this.comment = comment;
        this.commenter = commenter;
        this.time = time;
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PublicUser getCommenter() {
        return commenter;
    }

    public void setCommenter(PublicUser commenter) {
        this.commenter = commenter;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumLikes() {return numLikes;}

    public void setNumLikes(int numLikes) {this.numLikes = numLikes;}

    public boolean getLiked() {return liked;}

    public void setLiked(boolean liked) {this.liked = liked;}
}
