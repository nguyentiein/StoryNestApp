package com.example.sqllite;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.sqllite.DAO.RatingDao;
import com.example.sqllite.DAO.StoryDao;
import com.example.sqllite.Models.Comment;
import com.example.sqllite.Models.Rating;
import com.example.sqllite.Models.RatingWithUsernameAndComment;
import com.example.sqllite.Models.Story;
import com.example.sqllite.Models.User;
import com.example.sqllite.adapter.FeedbackAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FeedBackActivity extends AppCompatActivity {
    private TextView tvBookTitle;
    private RatingBar rbAverageRating;
    private TextView tvAverageRating;
    private RecyclerView rvFeedbacks;
    private FeedbackAdapter adapter;
    private List<RatingWithUsernameAndComment> ratings;

    private RatingBar rbUserRating;
    private EditText etUserComment;
    private Button btnSubmitFeedback;
    private String currentUserId;
    private AppDatabase db;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        tvBookTitle = findViewById(R.id.tvBookTitle);
        rbAverageRating = findViewById(R.id.rbAverageRating);
        tvAverageRating = findViewById(R.id.tvAverageRating);
        rvFeedbacks = findViewById(R.id.rvFeedbacks);
        rbUserRating = findViewById(R.id.rbUserRating);
        etUserComment = findViewById(R.id.etUserComment);
        btnSubmitFeedback = findViewById(R.id.btnSubmitFeedback);

        btnSubmitFeedback.setOnClickListener(v -> {
            int storyId = getIntent().getIntExtra("STORY_ID", -1);
            if (storyId != -1) {
                submitFeedback(storyId);
            }
        });

        executorService = Executors.newSingleThreadExecutor();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        currentUser = firebaseUser;

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name-v2").build();
        executorService.execute(() -> {
            User user = db.userDao().getUser(currentUser.getUid());
            if (currentUser != null && user != null) {

                currentUserId = user.userId;

                int storyId = getIntent().getIntExtra("STORY_ID", -1);
                if (storyId == -1 || currentUser == null) {
                    runOnUiThread(this::finish);
                    return;
                }

                loadFeedbacks(storyId);
            } else {
                runOnUiThread(() -> {
                    Toast.makeText(FeedBackActivity.this, "No user logged in", Toast.LENGTH_SHORT).show();
                    finish();
                });
            }
        });
    }
    private int getLatestChapterId(int storyId) {
        return db.chapterDao().getLatestChapterIdForStory(storyId);
    }

    private void submitFeedback(int storyId) {
        float rating = rbUserRating.getRating();
        String comment = etUserComment.getText().toString().trim();

        if (rating == 0) {
            Toast.makeText(this, "Please select a rating", Toast.LENGTH_SHORT).show();
            return;
        }

        executorService.execute(() -> {
            Rating newRating = new Rating();
            newRating.userId = currentUserId;
            newRating.storyId = storyId;
            newRating.rating = (int) rating;
            db.ratingDao().insertOrUpdateRating(newRating);

            if (!comment.isEmpty()) {
                Comment newComment = new Comment();
                newComment.userId = currentUserId;
                newComment.chapterId = getLatestChapterId(storyId);
                newComment.comment = comment;
                db.commentDao().insert(newComment);
            }

            runOnUiThread(() -> {
                Toast.makeText(FeedBackActivity.this, "Feedback submitted", Toast.LENGTH_SHORT).show();
                rbUserRating.setRating(0);
                etUserComment.setText("");
                loadFeedbacks(storyId);
            });
        });
    }

    private void loadFeedbacks(int storyId) {
        executorService.execute(() -> {
            Story story = db.storyDao().getbyIdStory(storyId);
            List<RatingWithUsernameAndComment> feedbacks = db.ratingDao().getRatingsWithUsernameAndCommentForStory(storyId);
            float averageRating = db.ratingDao().getAverageRatingForStory(storyId);

            runOnUiThread(() -> {
                tvBookTitle.setText(story.title);
                rbAverageRating.setRating(averageRating);
                tvAverageRating.setText(String.format("%.1f", averageRating));

                adapter = new FeedbackAdapter(feedbacks);
                rvFeedbacks.setLayoutManager(new LinearLayoutManager(this));
                rvFeedbacks.setAdapter(adapter);
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}