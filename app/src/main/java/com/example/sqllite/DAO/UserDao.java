package com.example.sqllite.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sqllite.Models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM Users WHERE userId = :userId")
    User getUser(String userId);

    @Query("SELECT * FROM Users")
    List<User> getAllUsers();
}
