package com.example.tjgaming.individualproject3.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tjgaming.individualproject3.R;

public class SelectionActivity extends AppCompatActivity {

    Button mParentBtn;
    Button mChildBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        mParentBtn = findViewById(R.id.parent_btn);
        mChildBtn = findViewById(R.id.child_btn);

        setListeners();
    }

    private void setListeners() {

        mParentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionActivity.this,ParentActivity.class));
            }
        });

        mChildBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionActivity.this,ChildActivity.class));
            }
        });
    }
}
