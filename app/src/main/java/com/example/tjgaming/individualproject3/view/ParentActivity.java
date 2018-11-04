package com.example.tjgaming.individualproject3.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tjgaming.individualproject3.R;
import com.example.tjgaming.individualproject3.controller.Database;

public class ParentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        Database.AsyncChildStatsTask task = new Database(this).new AsyncChildStatsTask(this);
        task.execute();
    }
}
