package com.example.sqllite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.sqllite.Models.Story;

public class DetailStory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_story);

        // Retrieve the Story object from Intent extras
        Intent intent = getIntent();
        Story story = intent.getParcelableExtra("story");

        // Populate UI elements with Story details
        ImageView imageViewCover = findViewById(R.id.imageViewCover);
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvAuthor = findViewById(R.id.tv_author);
        TextView tvDescription = findViewById(R.id.tv_description);

        Glide.with(this).load(story.getImage()).into(imageViewCover);
        tvTitle.setText(story.getTitle());
        tvAuthor.setText(story.getAuthor());
        tvDescription.setText(story.getDescription());

        // You can continue populating other UI elements like chapters RecyclerView, etc.
    }
}