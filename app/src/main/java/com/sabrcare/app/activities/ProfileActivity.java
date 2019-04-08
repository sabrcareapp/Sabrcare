package com.sabrcare.app.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sabrcare.app.R;
import com.sabrcare.app.auth.SignInActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView proUser;
    private Button proLogo;

    public static final String FILE="MyFile";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        proUser = findViewById(R.id.pro_user);
        proLogo = findViewById(R.id.pro_logo);

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
