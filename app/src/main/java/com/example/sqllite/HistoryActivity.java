package com.example.sqllite;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqllite.Models.HistoryItem;
import com.example.sqllite.adapter.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerViewHistory;
    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_view);

        recyclerViewHistory = findViewById(R.id.recyclerViewHistory);
        recyclerViewHistory.setLayoutManager(new GridLayoutManager(this, 2)); // 2 cột

        // Giả lập dữ liệu cứng với 8 giá trị
        List<HistoryItem> historyItemList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            historyItemList.add(new HistoryItem("History " + (i + 1)));
        }

        historyAdapter = new HistoryAdapter(historyItemList);
        recyclerViewHistory.setAdapter(historyAdapter);
    }
}
