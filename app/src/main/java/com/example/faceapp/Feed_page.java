package com.example.faceapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faceapp.adapters.CommentListAdapter;
import com.example.faceapp.adapters.PostsListAdapter;
import com.example.faceapp.entities.Post;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Feed_page extends AppCompatActivity {
    private Constraints constraints;
    private UserLocalStore userLocalStore;
    private PostsListAdapter adapter;
    private View menuLayout, commentsLayout, createPostLayout, postPicLayout;
    private RecyclerView listComments;

    //TODO: DELETE currentPostId after connecting to the database
    private int currentId, currentPostId;
    HashMap<String, CommentListAdapter> comments;

    //picture variables
    private ImageView picturePreview;
    private Uri imageUri, uri, uriPostPic;
    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private ActivityResultLauncher<Uri> mGetContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_page);
        constraints = new Constraints();
        Button logOut = findViewById(R.id.logOut);
        Button postButton = findViewById(R.id.postButton);
        Button photo_from_gallery = findViewById(R.id.post_photo_from_gallery);
        Button photo_from_camera = findViewById(R.id.post_photo_from_camera);
        ImageView addPostImage = findViewById(R.id.addPostImage);
        ImageView homeImage = findViewById(R.id.homeImage);
        ImageView menuImage = findViewById(R.id.menuImage);
        ImageView postComment = findViewById(R.id.postComment);
        TextView backToPosts = findViewById(R.id.backToPosts);
        View postPicLayout = findViewById(R.id.postPicLayout);
        EditText fillComment = findViewById(R.id.fillComment);
        menuLayout = findViewById(R.id.menuLayout);
        createPostLayout = findViewById(R.id.createPostLayout);
        TextView backToFeed = findViewById(R.id.backToFeed);
        comments = new HashMap<>();
        // Registers a photo picker activity launcher in single-select mode.
        pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uriPostPic -> {
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uriPostPic != null) {
                Log.d("PhotoPicker", "Selected URI: " + uriPostPic);
                imageUri = uriPostPic;
                picturePreview.setImageURI(imageUri);
            } else {
                Log.d("PhotoPicker", "No media selected");
            }
        });
        // Registers a photo picker activity launcher in single-select mode.
        mGetContent = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if (result) {
                            imageUri = uriPostPic;
                            picturePreview.setImageURI(imageUri);
                        } else {
                            Log.d("PhotoPicker", "No photo taken");
                        }
                    }
                });

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
        currentPostId = 0;
        for (currentPostId = 0; currentPostId < 10; currentPostId++) {
            Post post = new Post(publicUser.getName(), publicUser.getProfilePicture(), "I love gaming" + currentPostId, R.drawable.gamingsetup, currentPostId);
            posts.add(0, post);
            CommentListAdapter adapterListComment = new CommentListAdapter(this);
            adapterListComment.setComments(new ArrayList<Comment>());
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

        addPostImage.setOnClickListener(v -> {
            createPostLayout.setVisibility(View.VISIBLE);
        });

        photo_from_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picturePreview = new ImageView(Feed_page.this);
                picturePreview = findViewById(R.id.postPicturePreview);

                // Launch the photo picker and let the user choose only images.
                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            }
        });

        photo_from_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a unique file name based on timestamp and a unique identifier
                String fileName = "pic_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + ".jpg";
                File file = new File(getFilesDir(), fileName);

                // Get URI for the file using FileProvider
                uriPostPic = FileProvider.getUriForFile(Feed_page.this, getPackageName() + ".provider", file);
                mGetContent.launch(uriPostPic);

                picturePreview = new ImageView(Feed_page.this);
                picturePreview = findViewById(R.id.postPicturePreview);
            }
        });

        postButton.setOnClickListener(v -> {
            EditText postText = findViewById(R.id.post_fill_text);
            Boolean checkPost = false;
            Boolean picCheck= false;

            try {
                constraints.imageCheck(picturePreview);
                checkPost = true;
            } catch (Exception e) {
                Toast.makeText(this, "Please fill in the post, must include a picture.", Toast.LENGTH_SHORT).show();
            }
//            if (!postText.getText().toString().isEmpty()) {
//                checkPost = true;
//                try {
//                    constraints.imageCheck(picturePreview);
//                    picCheck = true;
//                } catch (Exception e) {
//                    Toast.makeText(this, "Please fill in the post, you have something to share don't you?", Toast.LENGTH_SHORT).show();
//                }
//            }
//            else {
//                try {
//                    constraints.imageCheck(picturePreview);
//                    checkPost = true;
//                    picCheck = true;
//                } catch (Exception e) {
//                    Toast.makeText(this, "No photo?", Toast.LENGTH_SHORT).show();
//                    imageUri = null;
//                }
//            }
            if (checkPost) {
                Post post = new Post(publicUser1.getName(), publicUser1.getProfilePicture(), postText.getText().toString(), imageUri, posts.size());
                imageUri = null;
                posts.add(0, post);
                adapter.setPosts(posts);
                createPostLayout.setVisibility(View.GONE);
                picturePreview.setImageResource(0);
                postText.setText("");
//                if (picCheck) {
//                    picturePreview.setImageResource(0);
//                    postPicLayout.setVisibility(View.GONE);
//                }
                CommentListAdapter adapterListComment = new CommentListAdapter(this);
                adapterListComment.setComments(new ArrayList<Comment>());
                comments.put(String.valueOf(post.getId()), adapterListComment);
            }
//            else {
//                Toast.makeText(this, "Please fill in the post, must include text and a picture.", Toast.LENGTH_SHORT).show();
//            }
        });

            backToFeed.setOnClickListener(v1 -> {
                createPostLayout.setVisibility(View.GONE);
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