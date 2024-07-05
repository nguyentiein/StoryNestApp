package com.example.sqllite.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.ColumnInfo;
import androidx.room.Index;

@Entity(
        tableName = "Favorites",
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "UserID", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Story.class, parentColumns = "storyId", childColumns = "StoryID", onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("UserID"), @Index("StoryID")}
)
public class Favorite {
    @PrimaryKey(autoGenerate = true)
    public int favoriteId;

    @ColumnInfo(name = "UserID") // Đảm bảo tên cột khớp với tên trong ForeignKey và Index
    public int userId;

    @ColumnInfo(name = "StoryID") // Đảm bảo tên cột khớp với tên trong ForeignKey và Index
    public int storyId;

    // Constructors, getters, and setters
    public Favorite(int userId, int storyId) {
        this.userId = userId;
        this.storyId = storyId;
    }
}
