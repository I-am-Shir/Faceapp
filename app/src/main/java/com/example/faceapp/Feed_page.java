package com.example.faceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.faceapp.adapters.PostsListAdapter;
import com.example.faceapp.entities.Post;

import java.util.ArrayList;
import java.util.List;

public class Feed_page extends AppCompatActivity {
    ImageView likePost, afterLikePost, commentPost, sharePost, savePost, afterSavePost, postPicture, postAuthor, postContent, postDate, deleteButton, reloadButton, postTitle;
    UserLocalStore userLocalStore;
    PostsListAdapter adapter;

    PublicUser publicUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        posts.add(new Post(publicUser.getName(), publicUser.getProfilePicture(), "I love gaming1", R.drawable.gamingsetup));
        posts.add(new Post(publicUser.getName(), publicUser.getProfilePicture(), "I love gaming2", R.drawable.gamingsetup));
        posts.add(new Post(publicUser.getName(), publicUser.getProfilePicture(),"I love gaming2", R.drawable.gamingsetup));
        posts.add(new Post(publicUser.getName(), publicUser.getProfilePicture(), "I love gaming4", R.drawable.gamingsetup));
        adapter.setPosts(posts);
    }


}