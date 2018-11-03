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


public class RegisterActivity extends AppCompatActivity {

    EditText mEmail;
    EditText mPassword;
    Button mRegBtn;
    Database mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        setListener();
        mDatabase = new Database(getApplicationContext());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setListener() {
        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CorrectData()) {
                    //Creates a user object
                    User user = new User(mEmail.getText().toString(), mPassword.getText().toString());
                    //Passes user object to be registered in database
                    mDatabase.register(user);
                    //If registered successfully, brought back to login page to login to app
                    Toast.makeText(getApplicationContext(),"User Created!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Text Fields not filled correctly.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Checks if the data being entered is not empty, that the first and last name are at least 3 characters long,
    // and that the email address is in valid format.
    private boolean CorrectData() {
        if (EmptyViews()) {
            return false;
        } else {
            return mEmail.getText().toString().contains("@") && mEmail.getText().toString().contains(".");
        }
    }

    //Checks if any field is empty, returns true or false
    private boolean EmptyViews() {
        return mEmail.getText().toString().isEmpty() || mPassword.getText().toString().isEmpty();
    }

    private void initViews() {
        mEmail = findViewById(R.id.email_address);
        mPassword = findViewById(R.id.password);
        mRegBtn = findViewById(R.id.register_btn);
    }
}