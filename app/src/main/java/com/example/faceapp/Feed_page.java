package com.example.faceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.faceapp.adapters.PostsListAdapter;
import com.example.faceapp.entities.Post;

import java.util.ArrayList;
import java.util.List;

public class Feed_page extends AppCompatActivity {
    private UserLocalStore userLocalStore;
    private PostsListAdapter adapter;
    private View menuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_page);
        Button logOut = findViewById(R.id.logOut);
        ImageView homeImage = findViewById(R.id.homeImage);
        ImageView menuImage = findViewById(R.id.menuImage);
        menuLayout = findViewById(R.id.menuLayout);

        userLocalStore = new UserLocalStore(this);
        if (!userLocalStore.getLoggedIn()) {
            Intent i = new Intent(Feed_page.this, Log_in_page.class);
            startActivity(i);
        }

        RecyclerView listPosts = findViewById(R.id.listPosts);
        final PostsListAdapter adapter = new PostsListAdapter(this);
        listPosts.setAdapter(adapter);
        listPosts.setLayoutManager(new LinearLayoutManager(this));
        Uri uri = Uri.parse("android.resource://your.package.here/drawable/profiAdle");
        PublicUser publicUser = new PublicUser();
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

        logOut.setOnClickListener(v -> {
            userLocalStore.clearUserData();
            userLocalStore.setUserLoggedIn(false);
            Intent i = new Intent(Feed_page.this, Log_in_page.class);
            startActivity(i);
            Toast.makeText(this, "Goodbye my friend", Toast.LENGTH_SHORT).show();
        });
    }




}