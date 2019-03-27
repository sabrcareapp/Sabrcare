package com.sabrcare.app.auth;


import android.content.Intent;
import android.content.SharedPreferences;
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

    TextInputEditText usernameTV;
    TextInputEditText passwordTV;
    TextInputEditText password_confirm;
    TextInputEditText emailTV;
    private Button signUp_btn;
    private RequestQueue questionRequestQueue;
    private Map<String,String> requestHeaders= new ArrayMap<String, String>();

    public static final String FILE="MyFile";
    SharedPreferences.Editor editor;


    private final String requestEndpoint= "http://api.remedley.com/api/client/signup";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameTV=findViewById(R.id.name);
        passwordTV=findViewById(R.id.password);
        password_confirm=findViewById(R.id.password2);
        emailTV=findViewById(R.id.email);
        signUp_btn =findViewById(R.id.register_btn);


        editor= getSharedPreferences(FILE,MODE_PRIVATE).edit();

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(passwordTV.getText().toString().equals(password_confirm.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Registering", Toast.LENGTH_LONG).show();
                    getAuthorizeUser(emailTV.getText().toString(),passwordTV.getText().toString(),usernameTV.getText().toString());

                }
            }
            private void getAuthorizeUser(String email, String password, String username){

                requestHeaders.put("email",email);
                requestHeaders.put("password",password);
                requestHeaders.put("firstName",username);

                questionRequestQueue = Volley.newRequestQueue(SignUpActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, requestEndpoint, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("serverResponse",response);
                            JSONObject responseData = new JSONObject(response);
                            String token = responseData.getString("token");
                            editor.putString("Token",token);
                            editor.apply();
                            Intent launchHome = new Intent(SignUpActivity.this,HomeActivity.class);


                            launchHome.putExtra("Filename",FILE);
                            startActivity(launchHome);
                            finishAffinity();

                        } catch (JSONException e) {
                            Log.d("signupError",e.toString());
                            Toast.makeText(SignUpActivity.this,"Could not Sign You Up!",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(">>>>>>>"+error.toString());
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
