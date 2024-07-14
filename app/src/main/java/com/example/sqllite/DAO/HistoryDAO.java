package com.example.sqllite.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sqllite.Models.HistoryItem;

import java.util.List;

@Dao
public interface HistoryDAO {
    @Insert
    void insert(HistoryItem historyItem);

    @Update
    void update(HistoryItem historyItem);

    @Delete
    void delete(HistoryItem historyItem);

    @Query("SELECT * FROM HistoryItems")
    List<HistoryItem> getAllHistoryItems();

    @Query("SELECT * FROM HistoryItems WHERE historyId = :id")
    HistoryItem getHistoryItemById(int id);

    @Query("SELECT * FROM HistoryItems WHERE userId = :userId")
    List<HistoryItem> getHistoryItemsByUserId(int userId);
}
