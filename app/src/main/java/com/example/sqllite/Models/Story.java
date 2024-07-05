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

    @ColumnInfo(name = "Title")
    public String title;

    @ColumnInfo(name = "Author")
    public String author;

    @ColumnInfo(name = "Genre")
    public String genre;

    @ColumnInfo(name = "Description")
    public String description;
}

