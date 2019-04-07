package com.sabrcare.app.timeline;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.sabrcare.app.ModelTimeline;
import com.sabrcare.app.R;
import com.sabrcare.app.activities.ProfileActivity;
import com.sabrcare.app.auth.SignInActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static com.sabrcare.app.HomeActivity.FILE;

public class TimelineFragment extends Fragment {
//    private RequestQueue timelineQueue;
//    private Map<String,String> timelineHeaders = new ArrayMap<String, String>();
    RecyclerView timeline_rv;
    ArrayList<ModelTimeline> timeline = new ArrayList<>();
    SharedPreferences setting;
    String token=null;
    public static Button browseTimeline;

    Button profile;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        View view= inflater.inflate(R.layout.fragment_timeline, container, false);

        timeline_rv = view.findViewById(R.id.timeline_rv);

        profile = view.findViewById(R.id.profile);



        setting= getActivity().getSharedPreferences(FILE,MODE_PRIVATE);

        token = setting.getString("Token","null");



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loadprofile = new Intent(getContext(),ProfileActivity.class);
                getContext().startActivity(loadprofile);

            }
        });

        if(token.equals("null"))
        {
            Intent launchHome = new Intent(getActivity(),SignInActivity.class);
            startActivity(launchHome);
            getActivity().finishAffinity();
            return view;
        }

        loadTimeline();//generate 10 items just for now.WIll be changed later
        return view;

    }

    private void loadTimeline() {

        Log.e("LOADTIMELINE","LOADTIMELINE CALLED");
        String requestEndpoint= getResources().getString(R.string.apiUrl)+"timeline/show";
        final Map<String,String> timelineHeaders = new ArrayMap<String, String>();

        timelineHeaders.put("token", token);
        //   timelineHeaders.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiaGFyaS4yNTk5QGdtYWlsLmNvLmluIiwiZXhwIjoxNTU0Mjk4OTUyfQ.qy7W-tdcSVGrEoZrNialM4VFURvX3UJ9o6Ifde5HN6s");
        RequestQueue timelineQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestEndpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("timelineResponse", response);

                   JSONArray timelineArray = new JSONObject(response).getJSONArray("data");
//                    JSONObject hello = timelineArray.getJSONObject(0);
//                    Log.e("PUSSY",hello.getString("recordsName"));
                    JSONObject timelineObject = null;
                    for(int i=0;i<timelineArray.length();i++)
                    {
                        timelineObject = timelineArray.getJSONObject(i);

                        ModelTimeline modelTimeline = new ModelTimeline();

                        if(timelineObject.getString("timelineType").equals("Record")) {
                            Log.e("INSIDE API",timelineObject.getString("recordsName"));

                            Date date = new Date(timelineObject.getJSONObject("date").getLong("$date"));
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:MM");
                            modelTimeline.setDate(simpleDateFormat.format(date));
                            modelTimeline.setTime(simpleTimeFormat.format(date));
                            modelTimeline.setTimelineType(timelineObject.getString("timelineType"));
                            modelTimeline.setTitle(timelineObject.getString("recordsName"));
                            modelTimeline.setSubtitle(timelineObject.getString("date"));
                           // modelTimeline.setImageUri("http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/256/Places-folder-red-icon.png");

                        }
                        else if (timelineObject.getString("timelineType").equals("Symptom")) {

                            Date date = new Date(timelineObject.getJSONObject("date").getLong("$date"));
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:MM");
                            modelTimeline.setDate(simpleDateFormat.format(date));
                            modelTimeline.setTime(simpleTimeFormat.format(date));
                            modelTimeline.setTimelineType(timelineObject.getString("timelineType"));
                            modelTimeline.setTitle(timelineObject.getString("symptomName"));
                            modelTimeline.setSubtitle(timelineObject.getString("symptomSeverity"));
                            //modelTimeline.setImageUri("http://icons.iconarchive.com/icons/dapino/medical-people/128/stethoscope-icon.png");

                        }
                        else if (timelineObject.getString("timelineType").equals("Medicine"))
                        {
                            Date date = new Date(timelineObject.getJSONObject("date").getLong("$date"));
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:MM");
                            modelTimeline.setDate(simpleDateFormat.format(date));
                            modelTimeline.setTime(simpleTimeFormat.format(date));
                            modelTimeline.setTimelineType(timelineObject.getString("timelineType"));
                            modelTimeline.setTitle(timelineObject.getString("medicineName"));
                            modelTimeline.setSubtitle(timelineObject.getString("medicineTimeHealthExpert"));
                            //modelTimeline.setImageUri("https://ibb.co/HBBdZdZ");
                            //http://icons.iconarchive.com/icons/icons8/windows-8/512/Healthcare-Pill-icon.png
                        }

                        timeline.add (modelTimeline);

                    }



                } catch (Exception e) {
                    //   Toast.makeText(getContext(), "Could not Sign You In!", Toast.LENGTH_LONG).show();
                }
                Log.e("TIMELINEFRAGMENT VALUES",timeline.size()+" ");
                setupRecyclerView(timeline);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(getContext(), "Could not Sign You in!", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return timelineHeaders;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                int mStatusCode = response.statusCode;
                Log.d("whats failing", String.valueOf(mStatusCode));
                return super.parseNetworkResponse(response);
            }
        };
        timelineQueue.add(stringRequest);
    }

    private void setupRecyclerView(ArrayList<ModelTimeline> timeline) {

        timeline_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        TimelineAdapter adapter = new TimelineAdapter(getContext(),timeline);
        timeline_rv.setAdapter(adapter);

    }
//    private void loadTimeline() {
//        String requestEndpoint= getResources().getString(R.string.apiUrl)+"timeline/show";
//        timelineHeaders.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiaGFyaS4yNTk5QGdtYWlsLmNvLmluIiwiZXhwIjoxNTU0Mjk4OTUyfQ.qy7W-tdcSVGrEoZrNialM4VFURvX3UJ9o6Ifde5HN6s");
//        timelineQueue = Volley.newRequestQueue(getContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestEndpoint, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//
//                    JSONArray timelineArray = new JSONArray(response);
//
//                    for(int i=0;i<timelineArray.length();i++)
//                    {
//
//                        String title=null,subtitle=null;
//                        Uri merchUri = null;
//                        JSONObject timelineObject = timelineArray.getJSONObject(i);
//
//                        if(timelineObject.getString("timelineType").equals("Record")) {
//                                title=timelineObject.getString("recordsName");
//                                subtitle=timelineObject.getString("date");
//                                merchUri =Uri.parse("http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/256/Places-folder-red-icon.png");
//
//                        }
//                        else if (timelineObject.getString("timelineType").equals("Symptom")) {
//                            title = timelineObject.getString("symptomName");
//                            subtitle = timelineObject.getString("symptomSeverity");
//                            merchUri =Uri.parse("http://icons.iconarchive.com/icons/dapino/medical-people/128/stethoscope-icon.png");
//
//                        }
//                        else if (timelineObject.getString("timelineType").equals("Medicine"))
//                        {
//                            title = timelineObject.getString("medicineName");
//                            subtitle = timelineObject.getString("medicineTimeHealthExpert");
//                            merchUri =Uri.parse("http://icons.iconarchive.com/icons/icons8/windows-8/512/Healthcare-Pill-icon.png");
//                        }
//
//                        timelineList.add (new ModelTimeline(title,subtitle,merchUri));
//
//                        }
//
//
//                    Log.d("timelineResponse", response);
//                } catch (Exception e) {
//                 //   Toast.makeText(getContext(), "Could not Sign You In!", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//               // Toast.makeText(getContext(), "Could not Sign You in!", Toast.LENGTH_LONG).show();
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() {
//                return timelineHeaders;
//            }
//
//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                int mStatusCode = response.statusCode;
//                Log.d("whats failing", String.valueOf(mStatusCode));
//                return super.parseNetworkResponse(response);
//            }
//        };
//        timelineQueue.add(stringRequest);
//     }
}