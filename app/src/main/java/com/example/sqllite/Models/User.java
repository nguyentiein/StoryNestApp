package com.example.sqllite.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.ColumnInfo;
import androidx.room.Index;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Users")
public class User {
    @PrimaryKey
    @NotNull
    public String userId;

    @ColumnInfo(name = "Username")
    public String username;

    @ColumnInfo(name = "Password")
    public String password;

    @ColumnInfo(name = "Email")
    public String email;

    public User() {
    }

    public User(@NotNull String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }
}
