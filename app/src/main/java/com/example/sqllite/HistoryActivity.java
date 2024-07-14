package com.example.sqllite;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sqllite.fragment.CartFragment;
import com.example.sqllite.fragment.ChangePasswordFragment;
import com.example.sqllite.fragment.HistoryFragment;
import com.example.sqllite.fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.example.sqllite.Models.HistoryItem;
import com.example.sqllite.adapter.HistoryAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final int MY_REQUEST_CODE = 10;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        historyItems = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            String title = "Truyện số " + i;
            String imageUrl = "https://cdn.chanhtuoi.com/uploads/2022/01/truyen-co-tich-1.jpg";
            String description = "Description for story " + i;
            HistoryItem item = new HistoryItem(i, title, imageUrl, description);
            historyItems.add(item);
        }

        historyAdapter = new HistoryAdapter(this, historyItems);
        historyListView = findViewById(R.id.historyListView);
        historyListView.setAdapter(historyAdapter);
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
                replaceFragment(new HistoryFragment());
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