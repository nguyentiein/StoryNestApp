package com.example.sqllite.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sqllite.Models.Rating;
import com.example.sqllite.Models.RatingWithUsernameAndComment;

import java.util.List;

@Dao
public interface RatingDao {
    @Query("SELECT Ratings.ratingId, Users.Username, Ratings.Rating, Ratings.StoryID, " +
            "COALESCE(Comments.Comment, '') as Comment " +
            "FROM Ratings " +
            "INNER JOIN Users ON Ratings.UserID = Users.userId " +
            "LEFT JOIN Comments ON Ratings.UserID = Comments.UserID AND Ratings.StoryID = " +
            "(SELECT storyId FROM Chapters WHERE chapterId = Comments.ChapterID) " +
            "WHERE Ratings.StoryID = :storyId")
    List<RatingWithUsernameAndComment> getRatingsWithUsernameAndCommentForStory(int storyId);

    @Query("SELECT AVG(Rating) FROM Ratings WHERE StoryID = :storyId")
    float getAverageRatingForStory(int storyId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdateRating(Rating rating);
}
