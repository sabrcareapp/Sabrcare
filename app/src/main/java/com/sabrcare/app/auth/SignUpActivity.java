package com.sabrcare.app.auth;


import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.sabrcare.app.R;
import com.sabrcare.app.HomeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText username;
    TextInputEditText password;
    TextInputEditText password_confirm;
    TextInputEditText email;
    private Button signUp_btn;
    private RequestQueue questionRequestQueue;
    private Map<String,String> requestHeaders= new ArrayMap<String, String>();


    private final String requestEndpoint= "http://api.remedley.com/api/client/signup";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        password_confirm=findViewById(R.id.password2);
        email=findViewById(R.id.email);
        signUp_btn =findViewById(R.id.register_btn);

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(password.getText().toString().equals(password_confirm.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Registered", Toast.LENGTH_LONG).show();
                    getAuthorizeUser();

                }
            }
            private void getAuthorizeUser(){

                requestHeaders.put("email",email.getText().toString());
                requestHeaders.put("password",password.getText().toString());
                requestHeaders.put("firstName",username.getText().toString());

                questionRequestQueue = Volley.newRequestQueue(SignUpActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, requestEndpoint, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseData = new JSONObject(response);
                            String token = responseData.getString("token");
                            Intent launchHome = new Intent(SignUpActivity.this,HomeActivity.class);
                            launchHome.putExtra("authToken",token);
                            startActivity(launchHome);
                            finishAffinity();

                        } catch (JSONException e) {
                            Toast.makeText(SignUpActivity.this,"Could not Sign You Up!",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this,"Could not Sign You Up!",Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() {
                        return requestHeaders;
                    }
                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        int mStatusCode = response.statusCode;
                        Log.d("whats failing", String.valueOf(mStatusCode));
                        return super.parseNetworkResponse(response);
                    }
                };
                questionRequestQueue.add(stringRequest);

            }
        });

    }
}
