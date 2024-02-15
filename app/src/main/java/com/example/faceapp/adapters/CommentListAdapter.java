package com.example.faceapp.adapters;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.faceapp.PublicUser;

public class CommentListAdapter RecyclerView.Adapter<CommentListAdapter.commentViewHolder> {

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private final PublicUser commenter;
        public ImageView like, liked, comment, share;
    }

}
