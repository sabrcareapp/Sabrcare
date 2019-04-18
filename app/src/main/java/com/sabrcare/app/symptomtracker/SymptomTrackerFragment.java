package com.sabrcare.app.symptomtracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sabrcare.app.LaunchActivity;
import com.sabrcare.app.R;
import com.sabrcare.app.activities.ProfileActivity;
import com.sabrcare.app.auth.SignInActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static com.sabrcare.app.HomeActivity.FILE;
import static com.sabrcare.app.symptomtracker.SymptomAddActivity.symptoms;

public class SymptomTrackerFragment extends Fragment {

    public int flag = 0, fl = 0;
    private RequestQueue symptomQueue;
    private Map<String, String> symptomHeaders = new ArrayMap<String, String>();
    SharedPreferences setting;
    String token = null;
    ImageView profile;
    Button sytBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_symptom_tracker, container, false);
        Toolbar symptom_tracker_toolbar = view.findViewById(R.id.symptom_toolbar);
        symptom_tracker_toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(symptom_tracker_toolbar);


        setting = getActivity().getSharedPreferences(FILE, MODE_PRIVATE);
        token = setting.getString("Token", "null");


        profile = view.findViewById(R.id.profile);
        profile.setColorFilter(Color.parseColor("#FFFFFF"));

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loadprofile = new Intent(getContext(), ProfileActivity.class);
                getContext().startActivity(loadprofile);

            }
        });

        if (token.equals("null")) {
            Intent launchHome = new Intent(getActivity(), LaunchActivity.class);
            startActivity(launchHome);
            getActivity().finishAffinity();
            return view;
        }

        Realm.init(getActivity());
        final Realm db = Realm.getDefaultInstance();


        if (db.where(ModelSymptom.class).count() != 0) {
            symptoms.addAll(db.where(ModelSymptom.class).findAll());
        }


        RecyclerView sytRv = view.findViewById(R.id.syt_rv);
        sytRv.setLayoutManager(new LinearLayoutManager(getContext()));
        SymptomAdapter symptomAdapter = new SymptomAdapter();
        sytRv.setAdapter(symptomAdapter);

        sytBtn = view.findViewById(R.id.syt_btn);

        com.github.clans.fab.FloatingActionButton sytFab = view.findViewById(R.id.syt_fab);
        sytFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                Intent intent = new Intent(getActivity(), SymptomAddActivity.class);
                startActivity(intent);
            }
        });

        sytBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < symptoms.size(); i++) {
                    if (symptoms.get(i).getIsCheck() == 1) {
                        fl = 1;
                        break;
                    }
                }
                if (fl == 0) {
                } else {
                    fl = 0;
                }
                for (int i = 0; i < 74; i++) {
                    if (symptoms.get(i).getIsCheck() == 1) {
                        if (symptoms.get(i).getSeverity().equals("null")) {
                            Toast.makeText(getContext(),
                                    "Please select the severity of each Symptom",
                                    Toast.LENGTH_SHORT);
                            return;
                        }
                    }
                }

                for (int i = 0; i < 74; i++) {
                    final int finalI = i;
                    db.executeTransaction(
                            new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    db.insertOrUpdate(symptoms.get(finalI));
                                }
                            }
                    );
                }

                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < 74; i++) {
                    if (symptoms.get(i).getIsCheck() == 1) {

                        JSONObject symptom = new JSONObject();
                        try {
                            symptom.put("symptomName", symptoms.get(i).getName());
                            symptom.put("symptomSeverity", symptoms.get(i).getSeverity());

                            jsonArray.put(symptom);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println(">>>>>>>>" + jsonArray);
                postDataItem(jsonArray);
            }
        });


        return view;
    }

    // TODO: Don't hardcode token
    private void postDataItem(JSONArray symptomArray) {
        symptomQueue = Volley.newRequestQueue(getContext());
        String url = getResources().getString(R.string.apiUrl) + "symptom/add";
        symptomHeaders.put("token", token);
        //   symptomHeaders.put("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiaGFyaS4yNTk5QGdtYWlsLmNvLmluIiwiZXhwIjoxNTU0Mjk4OTUyfQ.qy7W-tdcSVGrEoZrNialM4VFURvX3UJ9o6Ifde5HN6s")
        symptomHeaders.put("symptomArray", symptomArray.toString());
        StringRequest symptomAddition = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
                Toast.makeText(getContext(), "Symptom Added successfully", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("symerror", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return symptomHeaders;
            }
        };
        symptomQueue.add(symptomAddition);
    }
}