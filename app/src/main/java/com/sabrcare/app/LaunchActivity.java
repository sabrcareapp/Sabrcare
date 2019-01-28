package com.sabrcare.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity {

    Button introButton,loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        introButton = findViewById(R.id.ButtonIntro);
        loginButton = findViewById(R.id.loginbtn);

    }
}
