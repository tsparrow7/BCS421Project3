package com.example.tjgaming.individualproject3.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.tjgaming.individualproject3.R;


public class SplashScreenActivity extends Activity {

    final int WAIT_TIME = 1500; //1.5 seconds to display splash screen to user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, WAIT_TIME );
    }
}
