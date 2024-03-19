package com.example.faceapp.model;

import com.example.faceapp.model.Post;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostsResponse {

    @JsonProperty("posts")
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}