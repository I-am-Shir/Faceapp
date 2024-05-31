package com.example.faceapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

//    @JsonProperty("_id")
//    private String id;
    @JsonProperty("user_email")
    private String userEmail;
    @JsonProperty("user_firstName")
    private String userFirstName;
    @JsonProperty("user_lastName")
    private String userLastName;
    @JsonProperty("user_photo")
    private String userPhoto;
    @JsonProperty("pos*tBody")
    private String postBody;
    @JsonProperty("postPhoto")
    private String postPhoto;
    @JsonProperty("likesNumber")
    private int likesNumber;
    @JsonProperty("publication_date")
    private Date publicationDate;
    @JsonProperty("comments")
    private List<Comment> comments;
    @JsonProperty("commentsNumber")
    private int commentsNumber;
    @JsonProperty("publication_date_formatted")
    private String publicationDateFormatted;

//    public Post(String email, String firstName, String lastName, String string, String s, String imageUriString, int i, Date date, String s1) {
//    }
    public Post(String userEmail,
                String userFirstName,
                String userLastName,
                String userPhoto,
                String postBody,
                String postPhoto,
                int likesNumber,
                Date publicationDate,
                List<Comment> comments) {
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPhoto = userPhoto;
        this.postBody = postBody;
        this.postPhoto = postPhoto;
        this.likesNumber = likesNumber;
        this.publicationDate = publicationDate;
        this.comments = comments;
    }

//    public Post(String email, String firstName, String lastName, String ProfilePhoto, String postText, String postPhoto, int likesNumber, Date date, String comments) {
//    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getPostPhoto() {
        return postPhoto;
    }

    public void setPostPhoto(String postPhoto) {
        this.postPhoto = postPhoto;
    }

    public int getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(int likesNumber) {
        this.likesNumber = likesNumber;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

//    public String getPublicationDateFormatted() {
//        return publicationDateFormatted;
//    }
//
//    public void setPublicationDateFormatted(String publicationDateFormatted) {
//        this.publicationDateFormatted = publicationDateFormatted;
//    }

    public void addLike() {
        ++this.likesNumber;
    }

    public void removeLike() {
        --this.likesNumber;
    }
// TODO return delete

//    public String getId() {
//        return id;
//    }

//    public void setId(String id) {
//        this.id = id;
//    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    // Getters and setters
}