package com.example.faceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.faceapp.R;
import com.example.faceapp.entities.Post;

import java.util.List;

public class PostsListAdapter extends RecyclerView.Adapter<PostsListAdapter.PostsViewHolder>{

    public class PostsViewHolder extends RecyclerView.ViewHolder {
        //TODO: DELETE?
        //public TextView postTitle;
        private final TextView postContent;
        private final TextView postAuthor;
        private final ImageView postPicture;

        //public TextView postDate;
        //public Button deleteButton;
        //public Button reloadButton;
        private PostsViewHolder(View itemView) {
            super(itemView);
            postContent = itemView.findViewById(R.id.postContent);
            postAuthor = itemView.findViewById(R.id.postAuthor);
            postPicture = itemView.findViewById(R.id.postPicture);
            //postTitle = itemView.findViewById(R.id.postTitle);
            //postDate = itemView.findViewById(R.id.postDate);
            //deleteButton = itemView.findViewById(R.id.deleteButton);
            //reloadButton = itemView.findViewById(R.id.reload
        }
    }
    private final LayoutInflater mInflater;
    private List<Post> posts; // Cached copy of posts

    public PostsListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }
    //using the next two functions lets us recycle posts.
    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.post_layout, parent, false);
        return new PostsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostsViewHolder holder, int position) {
        if (posts != null) {
            final Post current = posts.get(position);
            holder.postContent.setText(current.getContent());
            holder.postAuthor.setText(current.getAuthor());
            holder.postPicture.setImageResource(current.getPicture());
        } else {
            // Covers the case of data not being ready yet.
            holder.postContent.setText("No Post");
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
