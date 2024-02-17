package com.example.faceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.faceapp.adapters.PostsListAdapter;
import com.example.faceapp.entities.Post;

import java.util.ArrayList;
import java.util.List;

public class Feed_page extends AppCompatActivity {
    ImageView likePost, afterLikePost, commentPost, sharePost, savePost, afterSavePost, postPicture, postAuthor, postContent, postDate, deleteButton, reloadButton, postTitle;
    private UserLocalStore userLocalStore;
    private PostsListAdapter adapter;
    private PublicUser publicUser;
    private ImageView homeImage, menuImage;
    private View menuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeImage = findViewById(R.id.homeImage);
        menuImage = findViewById(R.id.menuImage);
        menuLayout = findViewById(R.id.menuLayout);

        userLocalStore = new UserLocalStore(this);
        if (!userLocalStore.getLoggedIn()) {
            Intent i = new Intent(Feed_page.this, Log_in_page.class);
            startActivity(i);
        }
        setContentView(R.layout.activity_feed_page);

        RecyclerView listPosts = findViewById(R.id.listPosts);
        final PostsListAdapter adapter = new PostsListAdapter(this);
        listPosts.setAdapter(adapter);
        listPosts.setLayoutManager(new LinearLayoutManager(this));
        Uri uri = Uri.parse("android.resource://your.package.here/drawable/profiAdle");
        publicUser = new PublicUser();
        publicUser.setName("Shir");
        publicUser.setProfilePicture(uri);
        //TODO: DELETE
        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            posts.add(new Post(publicUser.getName(), publicUser.getProfilePicture(), "I love gaming" + i, R.drawable.gamingsetup, i));
        }
        adapter.setPosts(posts);

        homeImage.setOnClickListener(v -> {
            menuLayout.setVisibility(View.GONE);
        });
        menuImage.setOnClickListener(v -> {
            menuLayout.setVisibility(View.VISIBLE);
        });
    }




}