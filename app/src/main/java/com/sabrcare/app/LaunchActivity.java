package com.sabrcare.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    Button introButton,loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        introButton = findViewById(R.id.ButtonIntro);
        loginButton = findViewById(R.id.loginbtn);

        introButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchTutorial = new Intent(LaunchActivity.this,TutorialActivity.class);
                startActivity(launchTutorial);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lauchSignIn = new Intent(LaunchActivity.this,SignInActivity.class);
                startActivity(lauchSignIn);
            }
        });
    }
}
