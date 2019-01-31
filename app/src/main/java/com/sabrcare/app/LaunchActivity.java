package com.sabrcare.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    }
}
