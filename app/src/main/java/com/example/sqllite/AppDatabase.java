package com.example.sqllite;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sqllite.DAO.CartDAO;
import com.example.sqllite.DAO.CategoryDAO;
import com.example.sqllite.DAO.ChapterDao;
import com.example.sqllite.DAO.CommentDao;
import com.example.sqllite.DAO.FirmDAO;
import com.example.sqllite.DAO.HistoryDAO;
import com.example.sqllite.DAO.OrderDAO;
import com.example.sqllite.DAO.ProductDAO;
import com.example.sqllite.DAO.RatingDao;
import com.example.sqllite.DAO.StoryDao;
import com.example.sqllite.DAO.UserDao;
import com.example.sqllite.Models.Cart;
import com.example.sqllite.Models.Categories;
import com.example.sqllite.Models.Chapter;
import com.example.sqllite.Models.Comment;
import com.example.sqllite.Models.CommentHistory;
import com.example.sqllite.Models.Customer;
import com.example.sqllite.Models.DataConverter;
import com.example.sqllite.Models.Download;
import com.example.sqllite.Models.Favorite;
import com.example.sqllite.Models.Firm;
import com.example.sqllite.Models.HistoryItem;
import com.example.sqllite.Models.Order;
import com.example.sqllite.Models.OrderDetail;
import com.example.sqllite.Models.Products;
import com.example.sqllite.Models.Rating;
import com.example.sqllite.Models.RatingHistory;
import com.example.sqllite.Models.Story;
import com.example.sqllite.Models.User;
import com.example.sqllite.Models.UserSetting;

import androidx.room.TypeConverters;

@Database(entities = {HistoryItem.class,Cart.class, Firm.class, Products.class, Categories.class, Customer.class, Order.class, OrderDetail.class, User.class, Story.class, Chapter.class, Rating.class, Comment.class, Favorite.class, Download.class, UserSetting.class, RatingHistory.class, CommentHistory.class}, version = 4, exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDAO productDAO();
    public abstract CategoryDAO categoryDAO();
    public abstract FirmDAO firmDAO();
    public abstract OrderDAO orderDAO();
    public abstract CartDAO cartDAO();

    public abstract UserDao userDao();
   public abstract StoryDao storyDao();
   public abstract HistoryDAO historyDao();
    public abstract RatingDao ratingDao();
    public abstract CommentDao commentDao();
    public abstract ChapterDao chapterDao();


//    public abstract FavoriteDao favoriteDao();
//    public abstract DownloadDao downloadDao();
//    public abstract UserSettingDao userSettingDao();
//    public abstract RatingHistoryDao ratingHistoryDao();
//    public abstract CommentHistoryDao commentHistoryDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
