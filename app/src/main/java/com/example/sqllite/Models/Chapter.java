package com.example.sqllite.Models;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(
        tableName = "Chapters",
        foreignKeys = @ForeignKey(
                entity = Story.class,
                parentColumns = "storyId",
                childColumns = "StoryID",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("StoryID")}
)
public class Chapter {
    @PrimaryKey(autoGenerate = true)
    public int chapterId;

    @ColumnInfo(name = "StoryID") // Tên cột trùng khớp với tên cột trong schema của bạn
    public int storyId;

    @ColumnInfo(name = "ChapterNumber")
    public int chapterNumber;

    @ColumnInfo(name = "Title")
    public String title;

    @ColumnInfo(name = "Content")
    public String content;
}
