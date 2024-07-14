package com.example.sqllite.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sqllite.Models.Story;

import java.util.List;

@Dao
public interface StoryDao {
    @Insert
    void insert(Story... stories);
    @Query("SELECT * FROM Stories")
    List<Story> getAllStories();

    @Query("SELECT * FROM Stories WHERE storyId = :storyId LIMIT 1")
    Story getbyIdStory(int storyId);

    @Delete
    void deleteStory(Story story);
}


