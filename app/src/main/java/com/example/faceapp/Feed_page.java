package com.example.faceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.faceapp.adapters.PostsListAdapter;
import com.example.faceapp.entities.Post;

import java.util.ArrayList;
import java.util.List;

public class Feed_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_page);

        RecyclerView listPosts = findViewById(R.id.listPosts);
        final PostsListAdapter adapter = new PostsListAdapter(this);
        listPosts.setAdapter(adapter);
        listPosts.setLayoutManager(new LinearLayoutManager(this));

        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Shir", "I love gaming", R.drawable.gamingsetup));
        posts.add(new Post("Shir", "I love gaming1", R.drawable.gamingsetup));
        posts.add(new Post("Shir", "I love gaming2", R.drawable.gamingsetup));
        posts.add(new Post("Shir", "I love gaming3", R.drawable.gamingsetup));
        posts.add(new Post("Shir", "I love gaming4", R.drawable.gamingsetup));
        adapter.setPosts(posts);
    }
}