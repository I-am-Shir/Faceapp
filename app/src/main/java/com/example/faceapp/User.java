package com.example.faceapp;

import android.media.Image;
import android.net.Uri;

public class User {
    String first_name, last_name, username, password;
    Uri profile_picture;

    public User(String first_name, String last_name, String username, String password, Uri profile_picture) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.profile_picture = profile_picture;
    }
//    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//        this.first_name = "";
//        this.last_name = "";
//        this.profile_picture = null;
//    }
}
