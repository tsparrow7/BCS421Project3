package com.example.tjgaming.individualproject3.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tjgaming.individualproject3.R;
import com.example.tjgaming.individualproject3.controller.Database;
import com.example.tjgaming.individualproject3.model.Game;

import java.util.List;

public class ChildActivity extends AppCompatActivity
        implements DifficultyFragment.OnDifficultyChosenListener, LevelFragment.OnLevelChosenListener,LevelFragment.OnResumeListener {

    public static boolean beatLevel = false;

    Database db;
    List<Game> gameList;


    //Data to create a Level
    String mDifficulty;
    String mLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new DifficultyFragment())
                .commit();
    }

    @Override
    public void onDifficultyChosen(View view) {
        beatLevel = false;

        mDifficulty = ((Button) view).getText().toString();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new LevelFragment())
                .addToBackStack(LevelFragment.TAG)
                .commit();
    }

    @Override
    public void onLevelChosen(View view, String level) {
        beatLevel = false;
        mLevel = ((TextView) ((RelativeLayout) view).getChildAt(0)).getText().toString();


        Game game = new Game(mDifficulty,mLevel,beatLevel);
        startActivity(new Intent(this,GameActivity.class).putExtra("game",game));
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onResumed(View view) {

        if (beatLevel){
            db = new Database(ChildActivity.this);

            db.saveGame(new Game(mDifficulty,mLevel,beatLevel));
        }
    }
}