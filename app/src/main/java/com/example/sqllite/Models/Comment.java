package com.example.sqllite.Models;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.ColumnInfo;

@Entity(
        tableName = "Comments",
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "UserID", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Chapter.class, parentColumns = "chapterId", childColumns = "ChapterID", onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("UserID"), @Index("ChapterID")}
)
public class Comment {
    @PrimaryKey(autoGenerate = true)
    public int commentId;

    @ColumnInfo(name = "UserID") // Đảm bảo tên cột khớp với tên trong ForeignKey và Index
    public int userId;

    @ColumnInfo(name = "ChapterID") // Đảm bảo tên cột khớp với tên trong ForeignKey và Index
    public int chapterId;

    @ColumnInfo(name = "Comment")
    public String comment;
}
