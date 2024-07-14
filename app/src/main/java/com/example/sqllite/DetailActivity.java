package com.example.sqllite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgPro;
    private TextView txtTitle, txtAuthor, txtDescription,txtGenre;
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
        txtDescription = findViewById(R.id.tv_description);
        btnRead = findViewById(R.id.btnRead);
        btnRate = findViewById(R.id.btnRate);
        btnFa = findViewById(R.id.btnFa);
        btnShare = findViewById(R.id.btnShare);

        // Retrieve data from intent
        Intent intent = getIntent();
        if (intent != null) {
            String imageUrl = intent.getStringExtra("storyImage");
            String title = intent.getStringExtra("storyTitle");
            String author = intent.getStringExtra("storyAuthor");
            String genre = intent.getStringExtra("storyGenre");
            String description = intent.getStringExtra("storyDescription");

            // Populate UI elements with data
            Glide.with(this).load(imageUrl).into(imgPro);
            txtTitle.setText(title);
            txtAuthor.setText(author);
            txtGenre.setText(genre);
            txtDescription.setText(description);
        }
    }
}