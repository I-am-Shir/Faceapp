package com.example.faceapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.faceapp.entities.Comment;
import com.example.faceapp.PublicUser;
import com.example.faceapp.R;

import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentViewHolder> {

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private PublicUser commenter;
        private ImageView commenterProImage, postCommentEdit;
        private final TextView commentText, likeComment, editComment, deleteComment;
        private EditText editCommentFill;
        private TextView commenterName;
        private Comment comment;
        private int id;
        private View commentLayout, editCommentLayout;

        private Context context;

        public CommentViewHolder(View itemView) {
            super(itemView);
            likeComment = itemView.findViewById(R.id.likeComment);
            commentText = itemView.findViewById(R.id.commentText);
            editComment = itemView.findViewById(R.id.editComment);
            deleteComment = itemView.findViewById(R.id.deleteComment);
            commentLayout = itemView.findViewById(R.id.commentLayout);
            editCommentLayout = itemView.findViewById(R.id.editCommentLayout);
            postCommentEdit = itemView.findViewById(R.id.postCommentEdit);
            editCommentFill = itemView.findViewById(R.id.editCommentFill);


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
        View itemView = mInflater.inflate(R.layout.activity_comment, parent, false);
        return new CommentListAdapter.CommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (comments != null) {
            final Comment current = comments.get(position);
            holder.commenter = current.getCommenter();
            holder.commentText.setText(current.getComment());
            holder.likeComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (current.getLiked() == false) {
                        holder.likeComment.setTextColor(v.getResources().getColor(R.color.button_color, null));
                    } else {
                        holder.likeComment.setTextColor(v.getResources().getColor(R.color.black, null));
                    }
                }
            });
            holder.deleteComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comments.remove(current);
                    notifyItemRemoved(position);
                }
            });
            holder.editComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.commentLayout.setVisibility(View.GONE);
                    holder.editCommentLayout.setVisibility(View.VISIBLE);
                }
            });
            holder.postCommentEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.editCommentFill.getText().toString().length() > 0) {
                        current.setComment(holder.editCommentFill.getText().toString());
                        holder.commentText.setText(holder.editCommentFill.getText().toString());
                    }
                    holder.commentLayout.setVisibility(View.VISIBLE);
                    holder.editCommentLayout.setVisibility(View.GONE);
                }
            });

//            holder.editComment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    holder.commentText.setText("Edit Comment");
//                }

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

    public void addComment(Comment comment){
        comments.add(comment);
        notifyItemInserted(comments.size() - 1);
    }

}
