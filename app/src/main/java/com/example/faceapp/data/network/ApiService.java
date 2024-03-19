package com.example.faceapp.data.network;

import com.example.faceapp.model.FriendRequestRequestBody;
import com.example.faceapp.model.FriendshipRequestBody;
import com.example.faceapp.model.LoginResponse;
import com.example.faceapp.model.PostRequestBody;
import com.example.faceapp.model.User;
import com.example.faceapp.model.Post;
import com.example.faceapp.model.UserRequestBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.DELETE;
import retrofit2.http.Body;

public interface ApiService {
    interface Posts {
        @POST("posts/")
        Call<Post> addPost(@Header("authorization") String token, @Body PostRequestBody requestBody);

        @GET("posts/")
        Call<List<Post>> getPosts(@Header("authorization") String token);

        @GET("posts/{id}")
        Call<Post> getPostById(@Header("authorization") String token, @Path("id") String id);

        @PATCH("posts/{id}")
        Call<Post> editPost(@Header("authorization") String token, @Path("id") String id, @Body PostRequestBody requestBody);

        @DELETE("posts/{id}")
        Call<Post> deletePost(@Header("authorization") String token, @Path("id") String id);

        @GET("posts/{id}")
        Call<List<Post>> getUserPosts(@Header("authorization") String token, @Path("id") String id);
    }

    interface Tokens {
        @POST("tokens/")
        Call<LoginResponse> login(@Body User user);
    }

    interface Users {
        @POST("users/")
        Call<User> addUser(@Body UserRequestBody requestBody);

        @GET("users/{id}")
        Call<User> getUserByEmail(@Header("authorization") String token, @Path("id") String email);

        @PATCH("users/{id}")
        Call<User> updateUser(@Header("authorization") String token, @Path("id") String email, @Body UserRequestBody requestBody);

        @DELETE("users/{id}")
        Call<User> deleteUser(@Header("authorization") String token, @Path("id") String email);

        @GET("users/{id}/friends")
        Call<List<User>> getFriendList(@Header("authorization") String token, @Path("id") String email);

        @POST("sendFriendRequest/{id}")
        Call<String> sendFriendRequest(@Header("authorization") String token, @Path("id") String receiverEmail);

        @DELETE("rejectFriendRequest")
        Call<String> rejectFriendRequest(@Header("authorization") String token, @Body FriendRequestRequestBody requestBody);

        @PATCH("acceptFriendRequest")
        Call<String> acceptFriendRequest(@Header("authorization") String token, @Body FriendRequestRequestBody requestBody);

        @DELETE("deleteFriend")
        Call<String> deleteFriend(@Header("authorization") String token, @Body FriendshipRequestBody requestBody);
    }
}
