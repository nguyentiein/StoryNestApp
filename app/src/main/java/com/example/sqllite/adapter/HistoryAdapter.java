package com.example.sqllite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqllite.Holder.HistoryViewHolder;
import com.example.sqllite.Models.HistoryItem;
import com.example.sqllite.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {
    private List<HistoryItem> historyItemList;

    public HistoryAdapter(List<HistoryItem> historyItemList) {
        this.historyItemList = historyItemList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryItem item = historyItemList.get(position);
        holder.textViewHistoryItem.setText(item.getTitle());
        holder.imageViewHistoryItem.setImageResource(R.drawable.ic_history); // Ảnh cố định
    }

    @Override
    public int getItemCount() {
        return historyItemList.size();
    }
}
