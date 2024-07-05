package com.example.sqllite.Models;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.ColumnInfo;

@Entity(
        tableName = "UserSettings",
        foreignKeys = @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId", onDelete = ForeignKey.CASCADE),
        indices = {@Index("userId")}
)
public class UserSetting {
    @PrimaryKey(autoGenerate = true)
    public int settingId;

    @ColumnInfo(name = "userId") // Đảm bảo tên cột khớp với tên trong ForeignKey và Index
    public int userId;

    @ColumnInfo(name = "FontSetting")
    public String fontSetting;

    @ColumnInfo(name = "ThemeSetting")
    public String themeSetting;
}
