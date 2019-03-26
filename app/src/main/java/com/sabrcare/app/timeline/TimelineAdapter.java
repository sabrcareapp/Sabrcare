package com.sabrcare.app.timeline;

import android.content.Context;
import android.net.Uri;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineVH> {


    public Context ctx;

    ArrayList<ModelTimeline> timeline;


    public TimelineAdapter(Context ctx) {
        timeline = new ArrayList<>();
        this.ctx=ctx;
        loadTimeline();
        Log.e("INSIDE ADAPTER","HELLO");
    }

    @NonNull
    @Override
    public TimelineVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TimelineAdapter.TimelineVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineVH holder, int position) {

        Log.e("timelineValues",timeline.get(position).getTitle());
        holder.title.setText(timeline.get(position).getTitle());
        holder.subtitle.setText(timeline.get(position).getSubtitle());
        holder.pic.setImageURI(timeline.get(position).getImageUri());
    }

    @Override
    public int getItemCount() {
        return timeline.size();
    }

    public class TimelineVH extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView subtitle;
        public ImageView pic;               //will be changed to using Fresco library later

        public TimelineVH(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            subtitle=itemView.findViewById(R.id.subtitle);
            pic=itemView.findViewById(R.id.pic);

        }

    }

    private void loadTimeline() {

        Log.e("LOADTIMELINE","LOADTIMELINE CALLED");
        String requestEndpoint= ctx.getResources().getString(R.string.apiUrl)+"timeline/show";
        final Map<String,String> timelineHeaders = new ArrayMap<String, String>();
        timelineHeaders.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiaGFyaS4yNTk5QGdtYWlsLmNvLmluIiwiZXhwIjoxNTU0Mjk4OTUyfQ.qy7W-tdcSVGrEoZrNialM4VFURvX3UJ9o6Ifde5HN6s");
        RequestQueue timelineQueue = Volley.newRequestQueue(ctx);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestEndpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray timelineArray = new JSONArray(response);

                    for(int i=0;i<timelineArray.length();i++)
                    {

                        String title=null,subtitle=null;
                        Uri merchUri = null;
                        JSONObject timelineObject = timelineArray.getJSONObject(i);

                        if(timelineObject.getString("timelineType").equals("Record")) {
                            Log.e("INSIDE API",timelineObject.getString("recordsName"));
                            title=timelineObject.getString("recordsName");
                            subtitle=timelineObject.getString("date");
                            merchUri =Uri.parse("http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/256/Places-folder-red-icon.png");

                        }
                        else if (timelineObject.getString("timelineType").equals("Symptom")) {
                            title = timelineObject.getString("symptomName");
                            subtitle = timelineObject.getString("symptomSeverity");
                            merchUri =Uri.parse("http://icons.iconarchive.com/icons/dapino/medical-people/128/stethoscope-icon.png");

                        }
                        else if (timelineObject.getString("timelineType").equals("Medicine"))
                        {
                            title = timelineObject.getString("medicineName");
                            subtitle = timelineObject.getString("medicineTimeHealthExpert");
                            merchUri =Uri.parse("http://icons.iconarchive.com/icons/icons8/windows-8/512/Healthcare-Pill-icon.png");
                        }

                        Log.e("TITLE",title);
                        timeline.add (new ModelTimeline(title,subtitle,merchUri));

                    }


                    Log.d("timelineResponse", response);
                } catch (Exception e) {
                    //   Toast.makeText(getContext(), "Could not Sign You In!", Toast.LENGTH_LONG).show();
                }
                notifyDataSetChanged();
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
}
