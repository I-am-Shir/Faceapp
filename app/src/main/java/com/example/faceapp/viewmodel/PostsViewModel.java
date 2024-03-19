package com.example.faceapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.faceapp.data.repository.PostsRepository;
import com.example.faceapp.model.Post;
import com.example.faceapp.model.PostRequestBody;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class PostsViewModel extends ViewModel {

    private PostsRepository postsRepository;

    public PostsViewModel() {
        postsRepository = new PostsRepository();
    }

    public void addPost(String token, PostRequestBody requestBody, Callback<Post> callback) {
        postsRepository.addPost(token, requestBody, callback);
    }

    public LiveData<List<Post>> getPosts(String token) {
        MutableLiveData<List<Post>> postsLiveData = new MutableLiveData<>();
        postsRepository.getPosts(token, new Callback<List<Post>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    postsLiveData.setValue(response.body());
                } else {
                    // Handle error response if needed
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Post>> call, Throwable t) {
                // Handle network failure if needed
            }
        });
        return postsLiveData;
    }

    public void getPostById(String token, String id, Callback<Post> callback) {
        postsRepository.getPostById(token, id, callback);
    }

    public void editPost(String token, String id, PostRequestBody requestBody, Callback<Post> callback) {
        postsRepository.editPost(token, id, requestBody, callback);
    }

    public void deletePost(String token, String id, Callback<Post> callback) {
        postsRepository.deletePost(token, id, callback);
    }

    public LiveData<List<Post>> getUserPosts(String token, String id) {
        MutableLiveData<List<Post>> userPostsLiveData = new MutableLiveData<>();
        postsRepository.getUserPosts(token, id, new Callback<List<Post>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    userPostsLiveData.setValue(response.body());
                } else {
                    // Handle error response if needed
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Post>> call, Throwable t) {
                // Handle network failure if needed
            }
        });
        return userPostsLiveData;
    }
}