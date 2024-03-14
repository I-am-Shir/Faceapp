package com.example.faceapp.services;

import com.example.faceapp.User;
import com.example.faceapp.entities.Post;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.DELETE;
import retrofit2.http.PUT;
import retrofit2.http.Body;
import retrofit2.http.PATCH;

public interface ApiService {
    @POST("users/")
    Call<User> addUser(@Body User user);

    @GET("posts/{postId}")
    Call<Post> getPost(@Path("postId") int postId);

    // Add more methods for other API endpoints
}
