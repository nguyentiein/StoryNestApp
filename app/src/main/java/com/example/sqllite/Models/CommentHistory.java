package com.example.sqllite.Models;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.ColumnInfo;

@Entity(
        tableName = "CommentHistory",
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Comment.class, parentColumns = "commentId", childColumns = "commentId", onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("userId"), @Index("commentId")}
)
public class CommentHistory {
    @PrimaryKey(autoGenerate = true)
    public int historyId;

    @ColumnInfo(name = "userId") // Đảm bảo tên cột khớp với tên trong ForeignKey và Index
    public int userId;

    @ColumnInfo(name = "commentId") // Đảm bảo tên cột khớp với tên trong ForeignKey và Index
    public int commentId;

    @ColumnInfo(name = "PreviousComment")
    public String previousComment;

    @ColumnInfo(name = "NewComment")
    public String newComment;

    @ColumnInfo(name = "Timestamp")
    public String timestamp;
}
