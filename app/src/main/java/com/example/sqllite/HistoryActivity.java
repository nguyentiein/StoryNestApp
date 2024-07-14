package com.example.sqllite;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.example.sqllite.DAO.StoryDao;
import com.example.sqllite.Helper.StoryIdManager;
import com.example.sqllite.Models.Story;
import com.example.sqllite.fragment.CartFragment;
import com.example.sqllite.fragment.ChangePasswordFragment;
import com.example.sqllite.fragment.HistoryFragment;
import com.example.sqllite.fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.example.sqllite.Models.HistoryItem;
import com.example.sqllite.adapter.HistoryAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final int MY_REQUEST_CODE = 10;
    private AppDatabase db;

    private DrawerLayout drawerLayout;
    private static int FRAGMENT_HOME = 0;
    private static int FRAGMENT_CART = 1;
    private static int FRAGMENT_HISTORY = 2;
    private static int FRAGMENT_MY_PROFILE = 3;
    private static int FRAGMENT_CHANGE_PASSWORD = 4;
    private int currentFragment = FRAGMENT_HOME;
    private ListView historyListView;
    private HistoryAdapter historyAdapter;
    private ArrayList<HistoryItem> historyItems;
    private DrawerLayout drawer;
    private StoryIdManager storyIdManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name-v2").build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //get history from local
        storyIdManager = new StoryIdManager(this);
        List<String> savedIds = storyIdManager.getStoryIds();
//        for (String id : savedIds) {
//            System.out.println("Saved ID: " + id);
//        }
        historyItems = new ArrayList<>();

        if(savedIds.size() >0){
            StoryDao storyDao = db.storyDao();
            for (String idString : savedIds) {
            int id =Integer.parseInt(idString);
            try{
                Story story = storyDao.getbyIdStory(id);
                if(story!=null){
                    HistoryItem item = new HistoryItem(story.storyId, story.title, story.image, story.description, story.author,story.genre);
                    historyItems.add(item);
                }
            }catch (Exception e){
                continue;
            }

        }
        }
        if(savedIds == null || savedIds.isEmpty() || historyItems == null || historyItems.isEmpty()){
            for (int i = 1; i <= 8; i++) {
                String title = "Conan Movie " + i;
                String imageUrl = "https://www.detectiveconanworld.com/wiki/images/thumb/3/36/Detective_Conan_Movie_24.jpg/275px-Detective_Conan_Movie_24.jpg";
                String description = "Conan, who is disquieted, sneaks into the facility and finds that a female engineer has been kidnapped" + i;
                String author ="Phong Nguyen";
                String genre = "10/07/2023";
                HistoryItem item = new HistoryItem(i, title, imageUrl, description,author,genre);
                historyItems.add(item);
            }
        }




        historyAdapter = new HistoryAdapter(this, historyItems);
        historyListView = findViewById(R.id.historyListView);
        historyListView.setAdapter(historyAdapter);
        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HistoryItem selectedHistoryItem = (HistoryItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(HistoryActivity.this, DetailActivity.class);
                intent.putExtra("storyId", selectedHistoryItem.storyId);
                intent.putExtra("storyImage", selectedHistoryItem.getImage());
                intent.putExtra("storyTitle", selectedHistoryItem.getTitle());
                intent.putExtra("storyDescription", selectedHistoryItem.getDescription());
                intent.putExtra("storyAuthor", selectedHistoryItem.getAuthor());
                intent.putExtra("storyGenre", selectedHistoryItem.getGenre());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home){
            if (currentFragment != FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                currentFragment = FRAGMENT_HOME;
            }
        } else if (id == R.id.nav_fav) {
            if (currentFragment != FRAGMENT_CART){
                replaceFragment(new CartFragment());
                currentFragment = FRAGMENT_CART;
            }
        } else if (id == R.id.nav_history) {
            if (currentFragment != FRAGMENT_HISTORY){
                Intent intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                currentFragment = FRAGMENT_HISTORY;
            }
        }
        else if (id == R.id.nav_change_pass) {
            if (currentFragment != FRAGMENT_CHANGE_PASSWORD){
                replaceFragment(new ChangePasswordFragment());
                currentFragment = FRAGMENT_CHANGE_PASSWORD;
            }
        } else if (id == R.id.nav_signout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}