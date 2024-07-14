package com.example.sqllite.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqllite.R;

public class HistoryViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewHistoryItem;
    public ImageView imageViewHistoryItem;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewHistoryItem = itemView.findViewById(R.id.textViewHistoryItem);
        imageViewHistoryItem = itemView.findViewById(R.id.imageViewHistoryItem);
    }
}
