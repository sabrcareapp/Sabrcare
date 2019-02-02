package com.sabrcare.app;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SIGN UP";
    TextInputEditText username;
    TextInputEditText password;
    TextInputEditText name;
    Button signUp_btn;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        name=findViewById(R.id.name);

        viewPager=findViewById(R.id.container);
        signUp_btn =findViewById(R.id.register_btn);

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this,"REGISTERED!",Toast.LENGTH_LONG).show();
            }
        });

    }
}
