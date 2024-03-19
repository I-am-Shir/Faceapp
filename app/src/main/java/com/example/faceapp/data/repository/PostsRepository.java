package com.example.faceapp.data.repository;

import com.example.faceapp.data.network.ApiService;
import com.example.faceapp.data.network.RetrofitClient;
import com.example.faceapp.model.Post;
import com.example.faceapp.model.PostRequestBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class PostsRepository {
    private ApiService.Posts postsService;

    public PostsRepository() {
        postsService = RetrofitClient.getInstance().create(ApiService.Posts.class);
    }

    public void addPost(String token, Post postToAdd, Callback<Post> callback) {
        Call<Post> call = postsService.addPost(token, postToAdd);
        call.enqueue(callback);
    }

    public void getPosts(String token, Callback<List<Post>> callback) {
        Call<List<Post>> call = postsService.getPosts("bearer " + token);
        call.enqueue(callback);
    }

    public void getPostById(String token, String id, Callback<Post> callback) {
        Call<Post> call = postsService.getPostById(token, id);
        call.enqueue(callback);
    }

    public void editPost(String token, String id, PostRequestBody requestBody, Callback<Post> callback) {
        Call<Post> call = postsService.editPost(token, id, requestBody);
        call.enqueue(callback);
    }

    public void deletePost(String token, String id, Callback<Post> callback) {
        Call<Post> call = postsService.deletePost("bearer " + token, id);
        call.enqueue(callback);
    }

    public void getUserPosts(String token, String id, Callback<List<Post>> callback) {
        Call<List<Post>> call = postsService.getUserPosts(token, id);
        call.enqueue(callback);
    }
}