package com.example.sqllite.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(
        tableName = "HistoryItems",
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "UserID", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Story.class, parentColumns = "storyId", childColumns = "StoryId", onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("UserID"), @Index("StoryId")}
)
public class HistoryItem {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "historyId")
    public int historyId;

    @ColumnInfo(name = "UserID")
    public int userId;

    @ColumnInfo(name = "StoryId")
    public int storyId;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "Author")
    public String author;

    @ColumnInfo(name = "Genre")
    public String genre;
    public HistoryItem(int storyId, String title, String image, String description,String author, String genre) {
        this.storyId = storyId;
        this.title = title;
        this.image = image;
        this.description = description;
        this.author = author;
        this.genre = genre;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
