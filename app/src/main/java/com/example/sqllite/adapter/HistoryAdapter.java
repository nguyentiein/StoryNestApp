package com.example.sqllite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sqllite.Models.HistoryItem;
import com.example.sqllite.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter<HistoryItem> {

    private Context context;
    private ArrayList<HistoryItem> historyItems;

    public HistoryAdapter(Context context, ArrayList<HistoryItem> historyItems) {
        super(context, 0, historyItems);
        this.context = context;
        this.historyItems = historyItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
        }

        HistoryItem currentItem = historyItems.get(position);

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView descriptionTextView = convertView.findViewById(R.id.descriptionTextView);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        titleTextView.setText(currentItem.getTitle());
        descriptionTextView.setText(currentItem.getDescription());
        Picasso.get().load(currentItem.getImage()).into(imageView);

        return convertView;
    }
}
