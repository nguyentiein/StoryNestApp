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
}

