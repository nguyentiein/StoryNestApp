package com.example.sqllite.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.ColumnInfo;
import androidx.room.Index;

@Entity(tableName = "Users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int userId;

    @ColumnInfo(name = "Username")
    public String username;

    @ColumnInfo(name = "Password")
    public String password;

    @ColumnInfo(name = "Email")
    public String email;
}
