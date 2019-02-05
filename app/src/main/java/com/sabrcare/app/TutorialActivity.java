package com.sabrcare.app;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class TutorialActivity extends AppCompatActivity {

    private Button Sign_Up;
    private Button Log_In;

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


        Sign_Up = findViewById(R.id.SignUpBtn);
        Log_In = findViewById(R.id.loginbtn);

        Sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchSignUp = new Intent(TutorialActivity.this,SignUpActivity.class);
                startActivity(launchSignUp);
            }
        });

        Log_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchSignIn = new Intent(TutorialActivity.this,SignInActivity.class);
                startActivity(launchSignIn);
            }
        });

    }
}
