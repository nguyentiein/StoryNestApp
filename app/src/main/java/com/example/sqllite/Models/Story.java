package com.example.sqllite.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.ColumnInfo;
import androidx.room.Index;
@Entity(tableName = "Stories")
public class Story {
    @PrimaryKey(autoGenerate = true)
    public int storyId;

    @ColumnInfo(name = "Image")
    public String image;
    @ColumnInfo(name = "Title")
    public String title;

    @ColumnInfo(name = "Author")
    public String author;

    @ColumnInfo(name = "Genre")
    public String genre;

    @ColumnInfo(name = "Description")
    public String description;
    public Story(String image,String title, String author, String genre, String description) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

