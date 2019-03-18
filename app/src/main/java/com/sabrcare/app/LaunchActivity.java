package com.sabrcare.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.sabrcare.app.auth.SignInActivity;
import com.sabrcare.app.tutorial.TutorialActivity;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    Button introButton,loginButton;
    Animation from_bottom,from_side;
    TextView alread_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        introButton = findViewById(R.id.ButtonIntro);
        loginButton = findViewById(R.id.loginbtn);
        alread_user = findViewById(R.id.already_user);

        from_bottom = AnimationUtils.loadAnimation(this,R.anim.from_bottom);
        from_side   = AnimationUtils.loadAnimation(this,R.anim.from_side);
        introButton.setAnimation(from_bottom);
        loginButton.setAnimation(from_side);
        alread_user.setAnimation(from_side);
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
