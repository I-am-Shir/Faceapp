package com.example.faceapp.view;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faceapp.R;
import com.example.faceapp.model.User;
import com.example.faceapp.model.UserLocalStore;
import com.example.faceapp.utilities.Constraints;
import com.example.faceapp.view.adapters.CommentListAdapter;
import com.example.faceapp.view.adapters.PostsListAdapter;

import com.example.faceapp.utilities.JsonToJava;
import com.example.faceapp.model.PublicUser;
import com.example.faceapp.model.Comment;
import com.example.faceapp.model.Post;

import com.example.faceapp.viewmodel.PostsViewModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Feed_page extends AppCompatActivity {
    private PostsViewModel postsViewModel;
    private Constraints constraints;
    private UserLocalStore userLocalStore;
    private PostsListAdapter adapter;
    private View  menuLayout, commentsLayout, searchLayout, shareLayout, shareInnerLayout;
    private RecyclerView listComments;
    HashMap<String, CommentListAdapter> comments;

    //picture variables
    private ImageView picturePreview;
    private Uri imageUri, uriPostPic;
    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private ActivityResultLauncher<Uri> mGetContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initializing Posts View Model
        postsViewModel = new ViewModelProvider(this).get(PostsViewModel.class);
        // Creating a UserLocalStore instance to manage user data locally
        userLocalStore = new UserLocalStore(this);
        postsViewModel.init(userLocalStore);
        // Checking if a user is logged in
        if (!userLocalStore.getLoggedIn()) {
            Intent i = new Intent(Feed_page.this, Log_in_page.class);
            startActivity(i);
        }

        setContentView(R.layout.activity_feed_page);
        constraints = new Constraints();
        TextView userName = findViewById(R.id.userName);
        ImageView userPhoto = findViewById(R.id.userPhoto);
        Button logOut = findViewById(R.id.logOut);
        Button postButton = findViewById(R.id.postButton);
        ImageView searchImage = findViewById(R.id.searchImage);
        Button photo_from_gallery = findViewById(R.id.post_photo_from_gallery);
        Button photo_from_camera = findViewById(R.id.post_photo_from_camera);
        ImageView addPostImage = findViewById(R.id.addPostImage);
        ImageView homeImage = findViewById(R.id.homeImage);
        ImageView menuImage = findViewById(R.id.menuImage);
        ImageView postComment = findViewById(R.id.postComment);
        TextView backFromComments = findViewById(R.id.backFromComments);
        TextView backFromSearch = findViewById(R.id.backFromSearch);
        TextView backFromProfile = findViewById(R.id.backFromProfile);
        View createPostLayout = findViewById(R.id.createPostLayout);
        View refreshMenu = findViewById(R.id.refreshMenu);
        View userMenuLayout = findViewById(R.id.userMenuLayout);
        View refreshLayoutProfilePosts = findViewById(R.id.refreshLayoutProfilePosts);
        View refreshLayoutPosts = findViewById(R.id.refreshLayoutPosts);
        View titleFeedLayout = findViewById(R.id.titleFeedLayout);
        EditText fillComment = findViewById(R.id.fillComment);
        shareLayout = findViewById(R.id.shareLayout);
        shareInnerLayout = findViewById(R.id.shareInnerLayout);
        menuLayout = findViewById(R.id.menuLayout);
        searchLayout = findViewById(R.id.searchLayout);
        TextView backToFeed = findViewById(R.id.backToFeed);
        // Initializing the RecyclerView to display posts
        RecyclerView listPosts = findViewById(R.id.listPosts);
        TextView userShareName = findViewById(R.id.userShareName);
        ImageView userPhotoShare = findViewById(R.id.userPhotoShare);
        SwitchCompat darkModeSwitch = findViewById(R.id.darkModeSwitch);

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
        comments = new HashMap<>();
        userName.setText(userLocalStore.getLoggedInPublicUser().getName());
        userPhoto.setImageURI(userLocalStore.getLoggedInPublicUser().getProfilePicture());
        userShareName.setText(userLocalStore.getLoggedInPublicUser().getName());
        userPhotoShare.setImageURI(userLocalStore.getLoggedInPublicUser().getProfilePicture());

        // Retrieving the logged-in user's information
        PublicUser publicUser = userLocalStore.getLoggedInPublicUser();
        final PostsListAdapter adapter = new PostsListAdapter(this);
        listPosts.setAdapter(adapter);
        listPosts.setLayoutManager(new LinearLayoutManager(this));

//        //refresh posts
//        refreshPostsPage();

        // Setting up click listeners for UI elements
        // Click listener for the home button to hide the menu
        homeImage.setOnClickListener(v -> {
            refreshMenu.setVisibility(View.GONE);
            titleFeedLayout.setVisibility(View.VISIBLE);
            refreshLayoutPosts.setVisibility(View.VISIBLE);
            menuImage.setColorFilter(getResources().getColor(R.color.icon_color, null));
            homeImage.setColorFilter(getResources().getColor(R.color.next_stage_button, null));
        });
        // Click listener for the menu button to show the menu
        menuImage.setOnClickListener(v -> {
            refreshLayoutPosts.setVisibility(View.GONE);
            titleFeedLayout.setVisibility(View.GONE);
            refreshMenu.setVisibility(View.VISIBLE);
            homeImage.setColorFilter(getResources().getColor(R.color.icon_color, null));
            menuImage.setColorFilter(getResources().getColor(R.color.next_stage_button, null));

        });

        userMenuLayout.setOnClickListener(v -> {
            refreshLayoutProfilePosts.setVisibility(View.VISIBLE);
        });

        backFromProfile.setOnClickListener(v -> {
            refreshLayoutProfilePosts.setVisibility(View.GONE);
        });


        // Click listener for the post comment button to add a comment
        postComment.setOnClickListener(v -> {
            if (fillComment.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill in the comment", Toast.LENGTH_SHORT).show();
                return;
            } else {
                // Adding the comment to the corresponding post's comment list
                String fillComm = fillComment.getText().toString();
                fillComment.setText("");
                //comments.get(String.valueOf(currentId)).addComment(new Comment(fillComm, userLocalStore.getLoggedInPublicUser(), new Timestamp(System.currentTimeMillis()), currentId));
            }
        });

        // Click listener for the add post button to show the create post layout
        addPostImage.setOnClickListener(v -> {
            createPostLayout.setVisibility(View.VISIBLE);
        });

        // Click listener for the search button to show the search layout
        searchImage.setOnClickListener(v -> {
            searchLayout.setVisibility(View.VISIBLE);

        });

        // Click listener for the back button to hide the search layout
        backFromSearch.setOnClickListener(v -> {
            searchLayout.setVisibility(View.GONE);
        });


        // Set initial state of the switch based on the app's current night mode
        darkModeSwitch.setChecked(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES);

        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Enable dark mode if the switch is checked, otherwise use the default mode
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                recreate(); // Apply the new theme
            }
        });


//        colorMode.setOnClickListener(v -> {
//            // Switch between light and dark mode using
//            int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
//
//            // Toggle between light and dark mode
//            if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
//                // Set the app theme to light mode
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            } else {
//                // Set the app theme to dark mode
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            }
//        });

        // Setting up a click listener for the button to choose a photo from the gallery
        photo_from_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize the picture preview
                picturePreview = new ImageView(Feed_page.this);
                picturePreview = findViewById(R.id.postPicturePreview);

                // Launch the photo picker and let the user choose only images.
                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            }
        });

        // Setting up a click listener for the button to take a photo from the camera
        photo_from_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a unique file name based on timestamp and a unique identifier
                String fileName = "pic_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + ".jpg";
                File file = new File(getFilesDir(), fileName);

                // Get URI for the file using FileProvider
                uriPostPic = FileProvider.getUriForFile(Feed_page.this, getPackageName() + ".provider", file);
                mGetContent.launch(uriPostPic);
                // Initialize the picture preview
                picturePreview = new ImageView(Feed_page.this);
                picturePreview = findViewById(R.id.postPicturePreview);
            }
        });

        // Setting up a click listener for the post button
        postButton.setOnClickListener(v -> {
            EditText postText = findViewById(R.id.post_fill_text);
            Boolean checkPost = false;
            Boolean picCheck = false;

            // Check if the post contains an image
            try {
                constraints.imageCheck(picturePreview);
                checkPost = true;
            } catch (Exception e) {
                Toast.makeText(this, "Please fill in the post, must include a picture.", Toast.LENGTH_SHORT).show();
            }
            if (checkPost) {
                // If the post is valid, create a new post object and add it to the list of posts
                List<Post> posts = adapter.getPosts();
                User currentUser = userLocalStore.getLoggedInUser();
                Post post = new Post(null, currentUser.getFirstName(), currentUser.getLastName(), currentUser.getProfilePhoto().toString(), postText.getText().toString(), imageUri.toString(), 0, 0,  new Date(), "");
                addPost(post);
                posts.add(0, post);
                adapter.setPosts(posts);

                // Reset Layout
                imageUri = null;
                createPostLayout.setVisibility(View.GONE);
                picturePreview.setImageResource(0);
                postText.setText("");

                // Initialize a new CommentListAdapter for the current post
                CommentListAdapter adapterListComment = new CommentListAdapter(this);
                adapterListComment.setComments(new ArrayList<Comment>());
                //comments.put(String.valueOf(post.getId()), adapterListComment);
            }
        });
        // Setting up a click listener for the back button from creating post to return to the feed
        backToFeed.setOnClickListener(v1 -> {
            createPostLayout.setVisibility(View.GONE);
        });
        // Setting up a click listener for the log out button
        logOut.setOnClickListener(v -> {
            // Clear user data and go back to the login page
            userLocalStore.clearUserData();
            userLocalStore.setUserLoggedIn(false);
            Intent i = new Intent(Feed_page.this, Log_in_page.class);
            startActivity(i);
            Toast.makeText(this, "Goodbye my friend", Toast.LENGTH_SHORT).show();
        });
        // Setting up a click listener for the back button to return to the posts view
        backFromComments.setOnClickListener(v -> {
            commentsLayout = findViewById(R.id.commentsLayout);
            commentsLayout.setVisibility(View.GONE);
        });

        //setting up a click listener for the share so it'll exit it when clicked outside parameters.
        shareLayout.setOnClickListener(v -> {
            shareLayout.setVisibility(View.GONE);
        });

        //setting up a click listener for the shareInnerLayout so it won't exit when clicked inside parameters.
        shareInnerLayout.setOnClickListener(v -> {
        });

    }

    public void refreshPostsPage(){
        // Retrieving posts
        // Observe LiveData for posts
        postsViewModel.getPosts(userLocalStore.getToken()).observe(this, posts -> {
            // Update RecyclerView adapter with posts
            int index = posts.size();
            while (0 < index--) {
                Post post = posts.get(index);
                // Initializing a CommentListAdapter for each post
                CommentListAdapter adapterListComment = new CommentListAdapter(this);
                adapterListComment.setComments(post.getComments());
                // Storing the adapter in a HashMap with post ID as the key
                comments.put(String.valueOf(post.getId()), adapterListComment);
            }
            // Setting the posts to the adapter to display in the RecyclerView
            adapter.setPosts(posts);
        });
    }
    //does what the comment button should do so the comment adapter can do it from it's listener there.
    public void commentButton() {
        listComments = findViewById(R.id.listComments);
        //CommentListAdapter adapter = comments.get(String.valueOf(id));
        listComments.setAdapter(adapter);
        listComments.setLayoutManager(new LinearLayoutManager(this));
        commentsLayout = findViewById(R.id.commentsLayout);
        commentsLayout.setVisibility(View.VISIBLE);
    }

    public void shareButton(String id) {
        shareLayout.setVisibility(View.VISIBLE);
    }

    public void deletePost(Post postToDelete) {
        postsViewModel.deletePost(postToDelete, new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                // Handle successful deletion response if needed
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                // Handle deletion failure if needed
            }
        });
    }

    private void addPost(Post postToAdd) {
        postsViewModel.addPost(postToAdd, new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                // Handle successful addition of post if needed
                if (response.isSuccessful()) {
                    Toast.makeText(Feed_page.this, "Post saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Feed_page.this, "Server rejected the post", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                // Handle addition failure if needed
                Toast.makeText(Feed_page.this, "Error in post saving", Toast.LENGTH_SHORT).show();
            }
        });
    }
}