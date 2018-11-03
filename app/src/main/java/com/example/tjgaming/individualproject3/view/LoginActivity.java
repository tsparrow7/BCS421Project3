package com.example.tjgaming.individualproject3.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tjgaming.individualproject3.controller.Database;
import com.example.tjgaming.individualproject3.model.User;
import com.example.tjgaming.individualproject3.R;


public class LoginActivity extends AppCompatActivity {

    EditText mEmail;
    EditText mPassword;
    Button mLoginBtn;

    Database mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        setListener();
        mDatabase = new Database(getApplicationContext());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setListener() {
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correctData()) {
                    //Create user object
                    User user = new User(mEmail.getText().toString(), mPassword.getText().toString());
                    //Login with user object
                    mDatabase.login(user);
                    //Start the selection activity once user is logged in
                    Toast.makeText(getApplicationContext(), "Logged in successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, SelectionActivity.class));
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Text Fields not filled correctly.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean correctData() {
        if (emptyViews()) {
            return false;
        } else {
            return mEmail.getText().toString().contains("@") && mEmail.getText().toString().contains(".");
        }
    }

    private boolean emptyViews() {
        return mEmail.getText().toString().isEmpty() || mPassword.getText().toString().isEmpty();
    }

    private void initViews() {
        mEmail = findViewById(R.id.email_address);
        mPassword = findViewById(R.id.password);
        mLoginBtn = findViewById(R.id.login_btn);
    }
}