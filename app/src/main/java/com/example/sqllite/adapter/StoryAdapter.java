package com.example.sqllite.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.example.sqllite.DetailActivity;
import com.example.sqllite.Models.Story;
import com.example.sqllite.R;


import java.util.List;

public class StoryAdapter extends BaseAdapter {
    private Context context;
    private List<Story> stories;

    public StoryAdapter(Context context, List<Story> stories) {
        this.context = context;
        this.stories = stories;
    }

    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public Object getItem(int position) {
        return stories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return stories.get(position).storyId;
    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.liststoryitem, parent, false);
//        }
//
//        TextView title = convertView.findViewById(R.id.imageView);
//        TextView author = convertView.findViewById(R.id.story_author);
//
//        Story story = stories.get(position);
//        title.setText(story.title);
//        author.setText(story.author);
//
//        return convertView;
//    }


    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.liststoryitem, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView author = convertView.findViewById(R.id.story_tile);

        Story story = stories.get(position);

        Glide.with(context).load(story.image).into(imageView);
        author.setText(story.title);

        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("id", story.storyId);
            intent.putExtra("title", story.title);
            intent.putExtra("author", story.author);
            intent.putExtra("description", story.description);
            intent.putExtra("genre", story.genre);
            intent.putExtra("image", story.image);
            context.startActivity(intent);
        });

        return convertView;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
        notifyDataSetChanged();
    }
}
