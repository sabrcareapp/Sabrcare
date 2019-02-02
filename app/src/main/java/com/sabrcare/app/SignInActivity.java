package com.sabrcare.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "SIGN IN";
    TextInputEditText username;
    TextInputEditText password;
    Button signIn_btn;

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
            }
        });



    }
}
