package com.sabrcare.app.auth;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sabrcare.app.R;
import com.sabrcare.app.HomeActivity;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText username;
    private TextInputEditText password;
    private TextInputEditText name;
    private Button signUp_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        name=findViewById(R.id.name);

        signUp_btn =findViewById(R.id.register_btn);

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this,"REGISTERED!",Toast.LENGTH_LONG).show();

                Intent launchHome = new Intent(SignUpActivity.this,HomeActivity.class);
                startActivity(launchHome);
                finish();
            }
        });

    }
}
