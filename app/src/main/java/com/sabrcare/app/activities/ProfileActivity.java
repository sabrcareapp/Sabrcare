package com.sabrcare.app.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sabrcare.app.R;
import com.sabrcare.app.auth.SignInActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView proUser,about1,about2,terms1,terms2,privacy1,privacy2;
    private Button proLogo;
    private ImageView displayPic;

    public static final String FILE="MyFile";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        proUser = findViewById(R.id.pro_user);
        //TODO set user name

        proLogo = findViewById(R.id.pro_logo);
        displayPic = findViewById(R.id.Dp);
        displayPic.setColorFilter(Color.parseColor("#FFFFFF"));

        about1=findViewById(R.id.about_us);
        about2=findViewById(R.id.about1);
        about2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(about1.getMaxLines()==0)
                {
                    about1.setMaxLines(Integer.MAX_VALUE);
                }
                else {
                    about1.setMaxLines(0);
                }
            }
        });
        terms1=findViewById(R.id.terms);
        terms2=findViewById(R.id.tc1);
        terms2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(terms1.getMaxLines()==0)
                {
                    terms1.setMaxLines(Integer.MAX_VALUE);
                }
                else {
                    terms1.setMaxLines(0);
                }
            }
        });

        privacy1=findViewById(R.id.privacy1);
        privacy2=findViewById(R.id.privacy);
        privacy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(privacy2.getMaxLines()==0)
                {
                    privacy2.setMaxLines(Integer.MAX_VALUE);
                }
                else {
                    privacy2.setMaxLines(0);
                }
            }
        });

        proLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences1 = getSharedPreferences(FILE, Context.MODE_PRIVATE);
                sharedPreferences1.edit().clear().apply();

                Intent intent = new Intent(ProfileActivity.this, SignInActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

    }
}
