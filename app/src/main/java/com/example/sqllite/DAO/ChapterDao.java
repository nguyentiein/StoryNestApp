package com.example.sqllite.DAO;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface ChapterDao {
    @Query("SELECT MAX(chapterId) FROM Chapters WHERE storyId = :storyId")
    int getLatestChapterIdForStory(int storyId);
}
