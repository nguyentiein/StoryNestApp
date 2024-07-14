package com.example.sqllite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    private ImageView imgPro;
    private TextView txtTitle, txtAuthor, tvDescription, txtGenre, txtRate;
    private Button btnRead;
    private ImageButton btnRate, btnFa, btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize UI elements
        imgPro = findViewById(R.id.img_pro);
        txtTitle = findViewById(R.id.txt_Title);
        txtAuthor = findViewById(R.id.txt_Author);
        txtGenre = findViewById(R.id.txt_Genre);
        tvDescription = findViewById(R.id.tv_description);
        txtRate = findViewById(R.id.txt_Rate);
        btnRead = findViewById(R.id.btnRead);
        btnRate = findViewById(R.id.btnRate);
        btnFa = findViewById(R.id.btnFa);
        btnShare = findViewById(R.id.btnShare);

        Intent intent = getIntent();
        int storyId = intent.getIntExtra("id", -1);
        String title = intent.getStringExtra("title");
        String genre = intent.getStringExtra("genre");
        String author = intent.getStringExtra("author");
        String content = intent.getStringExtra("description");
        String image = intent.getStringExtra("image");
        float rating = intent.getFloatExtra("rating", 0.0f);

        txtTitle.setText(title);
        txtAuthor.setText(author);
        tvDescription.setText(content);
        txtGenre.setText(genre);
        txtRate.setText(String.format("%.1f", rating));
        Glide.with(this).load(image).into(imgPro);

        btnRate.setOnClickListener(v -> openFeedbackActivity(storyId));
        btnRead.setOnClickListener(v -> openReadBooktivity(genre));
    }

    private void openFeedbackActivity(int storyId) {
        if (storyId != -1) {
            Intent feedbackIntent = new Intent(this, FeedBackActivity.class);
            feedbackIntent.putExtra("STORY_ID", storyId);
            startActivity(feedbackIntent);
        } else {
            Toast.makeText(this, "Không thể mở trang đánh giá", Toast.LENGTH_SHORT).show();
        }
    }

    private void openReadBooktivity(String bookContent) {
        Intent readIntent = new Intent(this, ReadBookActivity.class);
        readIntent.putExtra("BOOK_CONTENT", bookContent);
        startActivity(readIntent);
    }
}