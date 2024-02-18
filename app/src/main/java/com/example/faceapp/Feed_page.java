package com.example.faceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faceapp.adapters.CommentListAdapter;
import com.example.faceapp.adapters.PostsListAdapter;
import com.example.faceapp.entities.Post;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Feed_page extends AppCompatActivity {
    private UserLocalStore userLocalStore;
    private PostsListAdapter adapter;
    private View menuLayout, commentsLayout;
    private RecyclerView listComments;
    private int currentId;
    HashMap<String, CommentListAdapter> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_page);
        Button logOut = findViewById(R.id.logOut);
        ImageView homeImage = findViewById(R.id.homeImage);
        ImageView menuImage = findViewById(R.id.menuImage);
        ImageView postComment = findViewById(R.id.postComment);
        TextView backToPosts = findViewById(R.id.backToPosts);
        EditText fillComment = findViewById(R.id.fillComment);
        menuLayout = findViewById(R.id.menuLayout);
        comments = new HashMap<>();

        userLocalStore = new UserLocalStore(this);
        if (!userLocalStore.getLoggedIn()) {
            Intent i = new Intent(Feed_page.this, Log_in_page.class);
            startActivity(i);
        }
        PublicUser publicUser1 = userLocalStore.getLoggedInPublicUser();

        RecyclerView listPosts = findViewById(R.id.listPosts);
        final PostsListAdapter adapter = new PostsListAdapter(this);
        listPosts.setAdapter(adapter);
        listPosts.setLayoutManager(new LinearLayoutManager(this));
        Uri uri = Uri.parse("android.resource://com.example.faceapp/drawable/profile");
        PublicUser publicUser = new PublicUser();
        publicUser.setName("Shir");
        publicUser.setProfilePicture(uri);
        //TODO: DELETE
        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Post post = new Post(publicUser.getName(), publicUser.getProfilePicture(), "I love gaming" + i, R.drawable.gamingsetup, i);
            posts.add(post);
            CommentListAdapter adapterListComment = new CommentListAdapter(this);
            comments.put(String.valueOf(post.getId()), adapterListComment);
        }
        adapter.setPosts(posts);

        homeImage.setOnClickListener(v -> {
            menuLayout.setVisibility(View.GONE);
        });
        menuImage.setOnClickListener(v -> {
            menuLayout.setVisibility(View.VISIBLE);
        });
        postComment.setOnClickListener(v -> {
            if (fillComment.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill in the comment", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                String fillComm = fillComment.getText().toString();
                fillComment.setText("");
                comments.get(String.valueOf(currentId)).addComment(new Comment(fillComm, publicUser, new Timestamp(System.currentTimeMillis()), currentId));
            }
        });

        logOut.setOnClickListener(v -> {
            userLocalStore.clearUserData();
            userLocalStore.setUserLoggedIn(false);
            Intent i = new Intent(Feed_page.this, Log_in_page.class);
            startActivity(i);
            Toast.makeText(this, "Goodbye my friend", Toast.LENGTH_SHORT).show();
        });
        backToPosts.setOnClickListener(v -> {
            commentsLayout = findViewById(R.id.commentsLayout);
            commentsLayout.setVisibility(View.GONE);
        });

    }
    public void commentButton(int id) {
        currentId = id;
        listComments = findViewById(R.id.listComments);
        CommentListAdapter adapter = comments.get(String.valueOf(id));
        listComments.setAdapter(adapter);
        listComments.setLayoutManager(new LinearLayoutManager(this));
        commentsLayout = findViewById(R.id.commentsLayout);
        commentsLayout.setVisibility(View.VISIBLE);
    }


}