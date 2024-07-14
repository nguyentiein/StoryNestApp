package com.example.sqllite.Models;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.ColumnInfo;

@Entity(
        tableName = "Ratings",
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "UserID", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Story.class, parentColumns = "storyId", childColumns = "StoryID", onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("UserID"), @Index("StoryID")}
)
public class Rating {
    @PrimaryKey(autoGenerate = true)
    public int ratingId;

    @ColumnInfo(name = "UserID") // Đảm bảo tên cột khớp với tên trong ForeignKey và Index
    public String userId;

    @ColumnInfo(name = "StoryID") // Đảm bảo tên cột khớp với tên trong ForeignKey và Index
    public int storyId;

    @ColumnInfo(name = "Rating")
    public int rating;
}
