package com.example.faceapp.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.faceapp.data.repository.PostsRepository;
import com.example.faceapp.model.Post;
import com.example.faceapp.model.PostRequestBody;
import com.example.faceapp.model.UserLocalStore;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class PostsViewModel extends ViewModel {

    private PostsRepository postsRepository;
    private UserLocalStore userLocalStore;
    private MutableLiveData<List<Post>> postsLiveData;

    public PostsViewModel() {
        postsRepository = new PostsRepository();
    }

    public void init(UserLocalStore userLocalStore) {
        this.userLocalStore = userLocalStore;
    }

    public void addPost(Post postToAdd, Callback<Post> callback) {
        String token = userLocalStore.getToken();
        postsRepository.addPost("Auth " + token, postToAdd, callback);
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
// TODO return delete

//    public void deletePost(Post postToDelete, Callback<Post> callback) {
//        String token = userLocalStore.getToken();
//        String id = postToDelete.getId();
//        postsRepository.deletePost(token, id, callback);
//    }

    public LiveData<List<Post>> getUserPosts(String token, String id) {
        postsRepository.getUserPosts(token, id, new Callback<List<Post>>() {
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

    public LiveData<List<Post>> getPostsLiveData() {
        return postsLiveData;
    }
}