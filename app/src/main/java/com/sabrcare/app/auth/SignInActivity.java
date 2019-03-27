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

public class SignInActivity extends AppCompatActivity {

    private TextInputEditText username;
    private TextInputEditText password;
    private Button signIn_btn;
    private RequestQueue signInQueue;
    private Map<String,String> requestHeaders= new ArrayMap<String, String>();

    private final String requestEndpoint= "http://api.remedley.com/api/client/login";

    public static String TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username=findViewById(R.id.usernameLogin);
        password=findViewById(R.id.passwordLogin);
        signIn_btn =findViewById(R.id.sign_in_btn);
        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInUser();
            }
        });

    }
    private void SignInUser(){

        requestHeaders.put("password",password.getText().toString());
        requestHeaders.put("email",username.getText().toString());

        signInQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, requestEndpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("SIgnInResponse",response);
                    JSONObject responseData = new JSONObject(response);
                    String token = responseData.getString("token");
                    Intent launchHome = new Intent(SignInActivity.this,HomeActivity.class);
                    launchHome.putExtra("authToken",token);
                    TOKEN=token;
                    startActivity(launchHome);
                    finishAffinity();

                } catch (JSONException e) {
                    Toast.makeText(SignInActivity.this,"Could not Sign You In!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignInActivity.this,"Could not Sign You in!",Toast.LENGTH_LONG).show();
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
        signInQueue.add(stringRequest);

    }
}



