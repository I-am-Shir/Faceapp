package com.example.faceapp.model;

import android.net.Uri;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and setter for profile picture URI (not serialized)
    public Uri getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(Uri profilePhoto) {
        this.profilePhoto = profilePhoto;
        this.profilePictureUriString = profilePhoto.toString(); // Convert Uri to String
    }

    // Custom getter for profile picture URI (serialized)
    public String getProfilePictureUriString() {
        return profilePictureUriString;
    }

    // Custom setter for profile picture URI (deserialized)
    public void setProfilePictureUriString(String profilePictureUriString) {
        this.profilePictureUriString = profilePictureUriString;
        this.profilePhoto = Uri.parse(profilePictureUriString); // Convert String to Uri
    }

    @JsonProperty("firstName")
    String firstName;
    @JsonProperty("lastName")
    String lastName;
    @JsonProperty("email")
    String email;
    @JsonProperty("password")
    String password;
    @JsonProperty("friends")
    List<String> friends;
    @JsonProperty("friend_reqs_sent")
    List<String> friend_reqs_sent;
    @JsonProperty("friend_reqs_received")
    List<String> friend_reqs_received;
    @JsonProperty("profilePhoto") // Use this annotation to specify the JSON field name
    private String profilePictureUriString; // Serialized form of the profile picture URI
    @JsonIgnore
    Uri profilePhoto; // This field will be ignored during serialization

    public User() {
        // Default constructor required by Jackson for deserialization
    }


    public User(String username, String password) {
        this.firstName = "";
        this.lastName = "";
        this.email = username;
        this.password = password;
        this.profilePhoto = Uri.parse("");
        this.profilePictureUriString = ""; // Convert Uri to String
    }

    public User(String email, String password, String first_name, String last_name, Uri profile_picture) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.email = email;
        this.password = password;
        this.profilePhoto = profile_picture;
        this.profilePictureUriString = profile_picture.toString(); // Convert Uri to String
    }
}
