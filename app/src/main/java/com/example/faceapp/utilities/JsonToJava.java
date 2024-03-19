
//package com.example.ap_project2_android;
//
//import android.content.Context;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.example.faceapp.model.Post;
//
//public class JsonToJava {

//    public static List<Post> parseJsonData(Context context) {
//        List<Post> posts = new ArrayList<>();
//        try {
//            InputStream inputStream = context.getAssets().open("db.json");
//            int size = inputStream.available();
//            byte[] buffer = new byte[size];
//            inputStream.read(buffer);
//            inputStream.close();
//            String jsonData = new String(buffer, "UTF-8");
//
//            JSONArray jsonArray = new JSONArray(jsonData);
//            for (int i = 0; i < Math.min(jsonArray.length(), 10); i++) {
//                JSONObject jsonPost = jsonArray.getJSONObject(i);
//                int id = jsonPost.getInt("id");
//                String userFirstName = jsonPost.getString("user_firstName"); // Fix: Correct key names
//                String userLastName = jsonPost.getString("user_lastName"); // Fix: Correct key names
//                String postBody = jsonPost.getString("postBody");
//                String postPhoto = jsonPost.optString("postPhoto"); // Use optString to handle possible null values
//                int likesNumber = jsonPost.getInt("likesNumber");
//                int commentsNumber = jsonPost.getInt("commentsNumber");
//                String publicationDate = jsonPost.getString("publication_date"); // Fix: Correct key names
//                Post post = new Post(id, userFirstName, userLastName, postBody, postPhoto, likesNumber, commentsNumber, publicationDate);
//
//                posts.add(post);
//            }
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//        return posts;
//    }
//}

package com.example.faceapp.utilities;

import android.net.Uri;

import com.example.faceapp.R;
import com.example.faceapp.model.Post;

public class JsonToJava {

    private String user_photo, authorName, timeStamp, postBody, postPhoto, publication_date;
    private String user_firstName, user_lastName;
    private int likesNumber;
    private int sharesCount;
    private int commentsNumber, id;

    public JsonToJava() {}

    public JsonToJava(int id, String user_firstName, String user_lastName,String user_photo, String postBody, String postPhoto, int likesNumber, int commentsNumber, String publication_date) {
        this();
        this.id = id;
        this.user_photo = user_photo;
        this.user_firstName = user_firstName;
        this.user_lastName = user_lastName;
        this.authorName = this.user_firstName + " " + this.user_lastName;
        this.timeStamp = timeStamp;
        this.postBody = postBody;
        this.postPhoto = postPhoto;
        this.likesNumber = likesNumber;
        this.commentsNumber = commentsNumber;
        this.sharesCount = sharesCount;
        this.publication_date = publication_date;
    }

    public int getId() {
        return id;
    }

    public String getUser_firstName() {
        return user_firstName;
    }

    public void setUser_firstName(String user_firstName) {
        this.user_firstName = user_firstName;
        setAuthorName(user_firstName + " " + user_lastName);
    }

    public String getUser_lastName() {
        return user_lastName;
    }

    public void setUser_lastName(String user_lastName) {
        this.user_lastName = user_lastName;
        setAuthorName(user_firstName + " " + user_lastName);
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = user_firstName + " " + user_lastName;
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

    public int getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(int commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    public int getSharesCount() {
        return sharesCount;
    }

    public void setSharesCount(int sharesCount) {
        this.sharesCount = sharesCount;
    }

    public String getPublication_date() {
        return publication_date;
    }

//    public Post toPost() {
//        if (postPhoto == null) {
//            return new Post(authorName,(user_photo==null ? null:Uri.parse(user_photo)), postBody, R.drawable.better__with, id);
//        }
//        else
//            return new Post(authorName,((user_photo == null) ? null : Uri.parse(user_photo)), postBody, Uri.parse(postPhoto), id);
//    }
}
