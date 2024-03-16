package com.example.faceapp.data.network;

import com.example.faceapp.model.User;
import com.example.faceapp.model.Post;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.DELETE;
import retrofit2.http.Body;

public interface ApiService {

    interface Users {
        @POST("users/")
        Call<User> addUser(@Body User user);
    }

    interface Tokens {
        @POST("tokens/")
        Call<User> login(@Body User user);
    }

    interface Posts {
        @GET("posts/{postId}")
        Call<Post> getUserPosts(@Path("+postId") int postId);

        @DELETE("posts/{id}")
        Call<Post> deletePost(@Path("postId") int id);
    }



    // Add more methods for other API endpoints
}
