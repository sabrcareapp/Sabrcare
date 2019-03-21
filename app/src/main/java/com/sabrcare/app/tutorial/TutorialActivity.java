package com.sabrcare.app.tutorial;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.sabrcare.app.R;
import com.sabrcare.app.auth.SignUpActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class TutorialActivity extends AppCompatActivity {

    private Button sign_up;
    private Button log_in;
    TextView already_user;
    Animation from_side;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        ViewPager pager =  findViewById(R.id.TutorialViewPager);
        TabLayout tabLayout =  findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        TutorialSlidesAdapter fragAdapter = new TutorialSlidesAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        pager.setAdapter(fragAdapter);
        tabLayout.setupWithViewPager(pager, true);

        from_side = AnimationUtils.loadAnimation(this,R.anim.from_side);
        sign_up = findViewById(R.id.SignUpBtn);
        log_in = findViewById(R.id.loginbtn);
        already_user=findViewById(R.id.tutorial_already_user);

        already_user.setAnimation(from_side);
        log_in.setAnimation(from_side);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchSignUp = new Intent(TutorialActivity.this,SignUpActivity.class);
                startActivity(launchSignUp);
            }
        });

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchSignIn = new Intent(TutorialActivity.this,SignInActivity.class);
                startActivity(launchSignIn);
            }
        });

    }
}
