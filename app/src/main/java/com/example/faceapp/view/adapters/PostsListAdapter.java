package com.example.faceapp.view.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.faceapp.view.Feed_page;
import com.example.faceapp.R;
import com.example.faceapp.model.Post;

import java.util.List;

public class PostsListAdapter extends RecyclerView.Adapter<PostsListAdapter.PostsViewHolder> {
    private Context context;
    private final LayoutInflater mInflater;
    private List<Post> posts; // Cached copy of posts
    public class PostsViewHolder extends RecyclerView.ViewHolder {

    //TODO: DELETE?
    //public TextView postTitle;
    private final TextView postContent, postAuthor;
    private TextView numLikes;
    private final EditText editPostContent;
    private final ImageView postPicture;
    private Button like, liked, comment, share;
    public ImageView deletePost, editPost, postEditedPost, posterProImage;
    private View editPostLayout;



//        public class ViewHolder extends RecyclerView.ViewHolder {
//
//             Handles the row being being clicked
//            @Override
//            public void onClick(View view) {
//                int position = getAbsoluteAdapterPosition(); // gets item position
//                if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
//                    User user = users.get(position);
//                    // We can access the data within the views
//                    Toast.makeText(context, tvName.getText(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }

        private PostsViewHolder(View itemView) {
            super(itemView);
            postContent = itemView.findViewById(R.id.postContent);
            postAuthor = itemView.findViewById(R.id.postAuthor);
            postPicture = itemView.findViewById(R.id.postPicture);
            like = itemView.findViewById(R.id.like);
            liked = itemView.findViewById(R.id.liked);
            comment = itemView.findViewById(R.id.commentText);
            share = itemView.findViewById(R.id.share);
            deletePost = itemView.findViewById(R.id.deletePost);
            editPost = itemView.findViewById(R.id.editPost);
            editPostContent = itemView.findViewById(R.id.editPostContent);
            postEditedPost = itemView.findViewById(R.id.postEditedPost);
            editPostLayout = itemView.findViewById(R.id.editPostLayout);
            posterProImage = itemView.findViewById(R.id.posterProImage);
            numLikes = itemView.findViewById(R.id.numLikes);
            //TODO: DELETE?
            //numComments = itemView.findViewById(R.id.numComments);
        }
    }

    public PostsListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
    //using the next two functions lets us recycle posts.
    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.post_layout, parent, false);
        return new PostsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (posts != null) {
            final Post current = posts.get(position);
            Uri profilePic = Uri.parse(current.getUserPhoto());
            String postAuthor = current.getUserFirstName() + " " + current.getUserLastName();

            holder.posterProImage.setImageURI(profilePic);
            if (profilePic == null)
                //holder.postPicture.setImageResource(R.drawable.ic_launcher_background);
                Glide.with(context).load(Uri.parse("https://home.adelphi.edu/~br21822/Ted.jpg")).into(holder.posterProImage);
            else
                Glide.with(context).load(profilePic).into(holder.posterProImage);
            holder.postContent.setText(current.getPostBody());
            holder.postAuthor.setText(postAuthor);
            if (current.getPostPhoto() != null && !current.getPostPhoto().isEmpty())
                Glide.with(context).load(Uri.parse(current.getPostPhoto())).into(holder.postPicture);
            else
                holder.postPicture.setImageResource(0);
            holder.numLikes.setText(String.valueOf(current.getLikesNumber()));
            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.like.setVisibility(View.GONE);
                    holder.liked.setVisibility(View.VISIBLE);
                    current.addLike();
                }
            });
            holder.liked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.liked.setVisibility(View.GONE);
                    holder.like.setVisibility(View.VISIBLE);
                    current.removeLike();
                }
            });

            holder.comment.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(context instanceof Feed_page){
                        ((Feed_page)context).commentButton();
                    }
                }
            });

            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(context instanceof Feed_page){
                        ((Feed_page)context).shareButton(current.getId());
                    }
                }
            });

            holder.editPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.postContent.setVisibility(View.GONE);
                    holder.editPostLayout.setVisibility(View.VISIBLE);
                }
            });

            holder.postEditedPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.editPostContent.getText().toString().length() > 0) {
                        current.setPostBody(holder.editPostContent.getText().toString());
                        holder.postContent.setText(holder.editPostContent.getText().toString());
                    }
                    holder.postContent.setVisibility(View.VISIBLE);
                    holder.editPostLayout.setVisibility(View.GONE);
                }
            });

            holder.deletePost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(context instanceof Feed_page){
                        ((Feed_page)context).deletePost();
                    }
                    posts.remove(current);
                    notifyItemRemoved(position);
                }
            });

        } else {
            // Covers the case of data not being ready yet.
            holder.postContent.setText("No Post Yet");
        }
    }

    public void setPosts(List<Post> posts){
        this.posts = posts;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // posts has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (posts != null)
            return posts.size();
        else return 0;
    }

    public List<Post> getPosts() {
        return posts;
    }


}
