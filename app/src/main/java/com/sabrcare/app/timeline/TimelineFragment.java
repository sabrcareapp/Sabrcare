package com.sabrcare.app.timeline;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sabrcare.app.ModelTimeline;
import com.sabrcare.app.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class TimelineFragment extends Fragment {




//    private RequestQueue timelineQueue;
//    private Map<String,String> timelineHeaders = new ArrayMap<String, String>();

    RecyclerView timeline_rv;


    ArrayList<ModelTimeline> timeline = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_timeline, container, false);

        timeline_rv = view.findViewById(R.id.timeline_rv);

        loadTimeline();//generate 10 items just for now.WIll be changed later
        return view;

    }

    private void loadTimeline() {

        Log.e("LOADTIMELINE","LOADTIMELINE CALLED");
        String requestEndpoint= getResources().getString(R.string.apiUrl)+"timeline/show";
        final Map<String,String> timelineHeaders = new ArrayMap<String, String>();
        timelineHeaders.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiaGFyaS4yNTk5QGdtYWlsLmNvLmluIiwiZXhwIjoxNTU0Mjk4OTUyfQ.qy7W-tdcSVGrEoZrNialM4VFURvX3UJ9o6Ifde5HN6s");
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

                            modelTimeline.setTitle(timelineObject.getString("recordsName"));
                            modelTimeline.setSubtitle(timelineObject.getString("date"));
                            modelTimeline.setImageUri("http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/256/Places-folder-red-icon.png");

                        }
                        else if (timelineObject.getString("timelineType").equals("Symptom")) {
                            modelTimeline.setTitle(timelineObject.getString("symptomName"));
                            modelTimeline.setSubtitle(timelineObject.getString("symptomSeverity"));
                            modelTimeline.setImageUri("http://icons.iconarchive.com/icons/dapino/medical-people/128/stethoscope-icon.png");

                        }
                        else if (timelineObject.getString("timelineType").equals("Medicine"))
                        {
                            modelTimeline.setTitle(timelineObject.getString("medicineName"));
                            modelTimeline.setSubtitle(timelineObject.getString("medicineTimeHealthExpert"));
                            modelTimeline.setImageUri("http://icons.iconarchive.com/icons/icons8/windows-8/512/Healthcare-Pill-icon.png");
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