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

public class SignInActivity extends AppCompatActivity {

    private TextInputEditText username;
    private TextInputEditText password;
    private Button signIn_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        signIn_btn =findViewById(R.id.sign_in_btn);
        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignInActivity.this,"SIGNED IN!",Toast.LENGTH_LONG).show();
                Intent launchHome = new Intent(SignInActivity.this,HomeActivity.class);
                launchHome.putExtra("username", username.toString());
                startActivity(launchHome);
                finishAffinity();
            }
        });
    }
}
