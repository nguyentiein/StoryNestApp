package com.example.sqllite.Models;

public class RatingWithUsernameAndComment {
    public int ratingId;
    public String Username;
    public int Rating;
    public int StoryID;
    public String Comment;

    public RatingWithUsernameAndComment() {
    }

    public RatingWithUsernameAndComment(int ratingId, String username, int rating, int storyID, String comment) {
        this.ratingId = ratingId;
        this.Username = username;
        this.Rating = rating;
        this.StoryID = storyID;
        this.Comment = comment;
    }
}