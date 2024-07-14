package com.example.sqllite.DAO;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.sqllite.Models.Comment;

@Dao
public interface CommentDao {
    @Insert
    void insert(Comment comment);
}
