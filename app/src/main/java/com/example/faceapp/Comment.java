package com.example.faceapp;

import android.widget.EditText;

import java.sql.Timestamp;

public class Comment {
    private EditText comment;
    private PublicUser commenter;
    private Timestamp time;
    private int id;

    public Comment(EditText comment, PublicUser commenter, Timestamp time, int id) {
        this.comment = comment;
        this.commenter = commenter;
        this.time = time;
        this.id = id;
    }

    public EditText getComment() {
        return comment;
    }

    public void setComment(EditText comment) {
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
}
