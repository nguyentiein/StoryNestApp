package com.example.sqllite.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class StoryIdManager {
    private static final String PREF_NAME = "StoryIdPref";
    private static final String KEY_STORY_IDS = "storyIds";
    private SharedPreferences sharedPreferences;

    public StoryIdManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    // Lưu một ID mới và đưa lên đầu nếu đã tồn tại
    public void addStoryId(String storyId) {
        List<String> currentIds = getStoryIds();
        if (currentIds.contains(storyId)) {
            currentIds.remove(storyId);  // Xóa ID cũ
        }
        currentIds.add(0, storyId);  // Đưa ID mới lên đầu
        saveStoryIds(currentIds);  // Lưu lại danh sách
    }

    // Lấy danh sách các ID đã lưu
    public List<String> getStoryIds() {
        return new ArrayList<>(sharedPreferences.getStringSet(KEY_STORY_IDS, Collections.emptySet()));
    }

    // Xóa một ID khỏi danh sách
    public void removeStoryId(String storyId) {
        List<String> currentIds = getStoryIds();
        currentIds.remove(storyId);
        saveStoryIds(currentIds);  // Lưu lại danh sách sau khi xóa
    }

    // Lưu trữ danh sách các ID
    private void saveStoryIds(List<String> storyIds) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(KEY_STORY_IDS, new HashSet<>(storyIds));
        editor.apply();
    }
}
