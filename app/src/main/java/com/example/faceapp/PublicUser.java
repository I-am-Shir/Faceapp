package com.example.faceapp;
import android.net.Uri;
public class PublicUser {
    private String name;
    private Uri profilePicture;

    public void setName(String name) {
        this.name = name;

    }
    public String getName() {
        return name;
    }

    public void setProfilePicture(Uri profilePicture) {
        this.profilePicture = profilePicture;
    }
    public Uri getProfilePicture() {
        return profilePicture;
    }


}
