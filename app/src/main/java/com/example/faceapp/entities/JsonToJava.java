package com.example.faceapp.entities;

import android.net.Uri;

import java.util.Random;

public class JsonToJava {
    private String authorImageSrc;
    private String authorName;
    private String timeStamp;
    private String postBody;
    private String postImageSrc;
    private String label;
    private String postTitle;
    private String postDescription;
    private int emojisCount;
    private int commentsCount;
    private int sharesCount;

    private Random rand;


    public JsonToJava() {
        rand = new Random();
    }
    public JsonToJava(String authorImageSrc, String authorName, String timeStamp, String postBody, String postImageSrc, String label, String postTitle, String postDescription, int emojisCount, int commentsCount, int sharesCount) {
        this();
        this.authorImageSrc = authorImageSrc;
        this.authorName = authorName;
        this.timeStamp = timeStamp;
        this.postBody = postBody;
        this.postImageSrc = postImageSrc;
        this.label = label;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.emojisCount = emojisCount;
        this.commentsCount = commentsCount;
        this.sharesCount = sharesCount;
    }

    public String getAuthorImageSrc() {
        return authorImageSrc;
    }

    public void setAuthorImageSrc(String authorImageSrc) {
        this.authorImageSrc = authorImageSrc;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getPostImageSrc() {
        return postImageSrc;
    }

    public void setPostImageSrc(String postImageSrc) {
        this.postImageSrc = postImageSrc;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public int getEmojisCount() {
        return emojisCount;
    }

    public void setEmojisCount(int emojisCount) {
        this.emojisCount = emojisCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getSharesCount() {
        return sharesCount;
    }

    public void setSharesCount(int sharesCount) {
        this.sharesCount = sharesCount;
    }

    public Post toPost() {
        return new Post(authorName, Uri.parse(authorImageSrc), postBody, Uri.parse(postImageSrc), rand.nextInt(1000));
    }
}
