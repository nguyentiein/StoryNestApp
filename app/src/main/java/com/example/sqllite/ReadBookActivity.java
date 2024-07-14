package com.example.sqllite;


import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ReadBookActivity extends AppCompatActivity {
    private TextView tvBookContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        tvBookContent = findViewById(R.id.tvBookContent);

        // Lấy nội dung sách từ Intent
        String bookContent = getIntent().getStringExtra("BOOK_CONTENT");
        if (bookContent != null) {
            tvBookContent.setText(bookContent);
        }
    }
}