package com.example.sqllite.Models;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.ColumnInfo;

@Entity(
        tableName = "RatingHistory",
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Story.class, parentColumns = "storyId", childColumns = "storyId", onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("userId"), @Index("storyId")}
)
public class RatingHistory {
    @PrimaryKey(autoGenerate = true)
    public int historyId;

    @ColumnInfo(name = "userId") // Đảm bảo tên cột khớp với tên trong ForeignKey và Index
    public int userId;

    @ColumnInfo(name = "storyId") // Đảm bảo tên cột khớp với tên trong ForeignKey và Index
    public int storyId;

    @ColumnInfo(name = "PreviousRating")
    public int previousRating;

    @ColumnInfo(name = "NewRating")
    public int newRating;

    @ColumnInfo(name = "Timestamp")
    public String timestamp;
}
