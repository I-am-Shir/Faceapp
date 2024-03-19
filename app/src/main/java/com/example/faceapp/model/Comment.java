package com.example.faceapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonGetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
    private String commentId;
    private String commenterFirstName;
    private String commenterLastName;
    private String commenterPhoto;
    private String commentBody;

    @JsonProperty("comment_id")
    public String getCommentId() {
        return commentId;
    }

    @JsonSetter("comment_id")
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    @JsonProperty("commenter_firstName")
    public String getCommenterFirstName() {
        return commenterFirstName;
    }

    @JsonSetter("commenter_firstName")
    public void setCommenterFirstName(String commenterFirstName) {
        this.commenterFirstName = commenterFirstName;
    }

    @JsonProperty("commenter_lastName")
    public String getCommenterLastName() {
        return commenterLastName;
    }

    @JsonSetter("commenter_lastName")
    public void setCommenterLastName(String commenterLastName) {
        this.commenterLastName = commenterLastName;
    }

    @JsonProperty("commenter_photo")
    public String getCommenterPhoto() {
        return commenterPhoto;
    }

    @JsonSetter("commenter_photo")
    public void setCommenterPhoto(String commenterPhoto) {
        this.commenterPhoto = commenterPhoto;
    }

    @JsonProperty("commentBody")
    public String getCommentBody() {
        return commentBody;
    }

    @JsonSetter("commentBody")
    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }
}