package com.example.sqllite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqllite.Models.RatingWithUsernameAndComment;
import com.example.sqllite.R;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {
    private List<RatingWithUsernameAndComment> feedbacks;

    public FeedbackAdapter(List<RatingWithUsernameAndComment> feedbacks) {
        this.feedbacks = feedbacks;
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feedback, parent, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        RatingWithUsernameAndComment feedback = feedbacks.get(position);
        holder.tvUsername.setText(feedback.Username);
        holder.rbRating.setRating(feedback.Rating);
        holder.tvComment.setText(feedback.Comment);
    }

    @Override
    public int getItemCount() {
        return feedbacks.size();
    }

    static class FeedbackViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername;
        RatingBar rbRating;
        TextView tvComment;

        FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            rbRating = itemView.findViewById(R.id.rbRating);
            tvComment = itemView.findViewById(R.id.tvComment);
        }
    }
}