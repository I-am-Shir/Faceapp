package com.example.faceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.faceapp.Feed_page;
import com.example.faceapp.R;
import com.example.faceapp.entities.Post;

import java.util.List;

public class PostsListAdapter extends RecyclerView.Adapter<PostsListAdapter.PostsViewHolder> {
    private Context context;
    private final LayoutInflater mInflater;
    private List<Post> posts; // Cached copy of posts
    public class PostsViewHolder extends RecyclerView.ViewHolder {

        //TODO: DELETE?
        //public TextView postTitle;
        private final TextView postContent;
        private final TextView postAuthor;
        private final ImageView postPicture;
        public ImageView like, liked, comment, share;
        private View commentedLayout, commentLayout;



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


        //public TextView postDate;
        //public Button deleteButton;
        //public Button reloadButton;
        private PostsViewHolder(View itemView) {
            super(itemView);
            postContent = itemView.findViewById(R.id.postContent);
            postAuthor = itemView.findViewById(R.id.postAuthor);
            postPicture = itemView.findViewById(R.id.postPicture);
            like = itemView.findViewById(R.id.like);
            liked = itemView.findViewById(R.id.liked);
            comment = itemView.findViewById(R.id.commentText);
            share = itemView.findViewById(R.id.share);
            commentedLayout = itemView.findViewById(R.id.commentedLayout);
            commentLayout = itemView.findViewById(R.id.commentLayout);


            //postTitle = itemView.findViewById(R.id.postTitle);
            //postDate = itemView.findViewById(R.id.postDate);
            //deleteButton = itemView.findViewById(R.id.deleteButton);
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
    public void onBindViewHolder(PostsViewHolder holder, int position) {
        if (posts != null) {
            final Post current = posts.get(position);
            holder.postContent.setText(current.getContent());
            holder.postAuthor.setText(current.getAuthor());
            if (current.getPicture() == 0)
                holder.postPicture.setImageURI(current.getUriPicture());
            else
                holder.postPicture.setImageResource(current.getPicture());
            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.like.setVisibility(View.GONE);
                    holder.liked.setVisibility(View.VISIBLE);
                }
            });
            holder.liked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.liked.setVisibility(View.GONE);
                    holder.like.setVisibility(View.VISIBLE);
                }
            });

            holder.comment.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(context instanceof Feed_page){
                        ((Feed_page)context).commentButton(current.getId());
                    }
                }
            });

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
