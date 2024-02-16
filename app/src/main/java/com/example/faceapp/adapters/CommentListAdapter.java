package com.example.faceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.faceapp.Comment;
import com.example.faceapp.PublicUser;
import com.example.faceapp.R;
import com.example.faceapp.entities.Post;

import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentViewHolder> {

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private final PublicUser commenter;
        public ImageView like, liked, comment, share;

        private Context context;

        public CommentViewHolder(View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.comment);

            //this.commenter = itemView.findViewById(R.id.commenterName);;
        }
    }

    private final LayoutInflater mInflater;
    private List<Comment> comments; // Cached copy of posts

    public CommentListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CommentListAdapter.CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.post_layout, parent, false);
        return new CommentListAdapter.CommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        if (comments != null) {
            final Comment current = comments.get(position);
            holder.commentText.setText(current.getContent());
            holder.commentAuthor.setText(current.getAuthor());
            holder.commentPicture.setImageResource(current.getPicture());
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

        } else {
            // Covers the case of data not being ready yet.
            holder.commentText.setText("No Post");
        }
    }

    public void setComments(List<Comment> comments){
        this.comments = comments;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // posts has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (comments != null)
            return comments.size();
        else return 0;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
