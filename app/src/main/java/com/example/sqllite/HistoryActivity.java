package com.example.sqllite;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sqllite.Models.HistoryItem;
import com.example.sqllite.adapter.HistoryAdapter;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ListView historyListView;
    private HistoryAdapter historyAdapter; // Sửa tại đây
    private ArrayList<HistoryItem> historyItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_view);

        historyItems = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            String title = "Truyện số " + i;
            String imageUrl = "https://cdn.chanhtuoi.com/uploads/2022/01/truyen-co-tich-1.jpg";
            String description = "Description for story ";
            HistoryItem item = new HistoryItem(i, title, imageUrl, description);
            historyItems.add(item);
        }

        // Khởi tạo và thiết lập adapter
        historyAdapter = new HistoryAdapter(this, historyItems);
        historyListView = findViewById(R.id.historyListView);
        historyListView.setAdapter(historyAdapter);
    }
}
