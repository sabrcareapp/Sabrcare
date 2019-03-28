package com.sabrcare.app.timeline;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sabrcare.app.HomeActivity;
import com.sabrcare.app.ModelTimeline;
import com.sabrcare.app.HomeActivity.*;
import com.sabrcare.app.R;
import com.sabrcare.app.medicine.MedicineFragment;
import com.sabrcare.app.records.RecordsFragment;
import com.sabrcare.app.symptomtracker.SymptomTrackerFragment;

import org.json.JSONArray;
import org.json.JSONObject;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ReportFragment;
import androidx.recyclerview.widget.RecyclerView;

import static java.security.AccessController.getContext;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineVH> {


    public Context ctx;

    ArrayList<ModelTimeline> timeline;

    RequestOptions options;


    public TimelineAdapter(Context ctx,ArrayList<ModelTimeline> timeline) {
        this.timeline=timeline;
        this.ctx=ctx;
        options = new RequestOptions().centerCrop().placeholder(android.R.drawable.alert_dark_frame).error(android.R.drawable.alert_dark_frame);
        Log.e("INSIDE ADAPTER","HELLO");
    }

    @NonNull
    @Override
    public TimelineVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TimelineAdapter.TimelineVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineVH holder, int position) {

        Log.e("INSIDE ONBIND",timeline.get(position).getTitle());
      //  Log.e("timelineValues",timeline.get(position).getTitle());
        holder.title.setText(timeline.get(position).getTitle());
        holder.subtitle.setText(timeline.get(position).getSubtitle());

        if(timeline.get(position).getTimelineType().equals("Record"))
            holder.pic.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_records_image));
       else if(timeline.get(position).getTimelineType().equals("Medicine"))
            holder.pic.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_pills_solid));

        else if(timeline.get(position).getTimelineType().equals("Symptom"))
            holder.pic.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_stethoscope_solid));


    }




    @Override
    public int getItemCount() {
        return timeline.size();
    }

    public class TimelineVH extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView subtitle;
        public ImageView pic;
        public Button browseTimeline;//will be changed to using Fresco library later

        public TimelineVH(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            subtitle=itemView.findViewById(R.id.subtitle);
            pic=itemView.findViewById(R.id.pic);
            browseTimeline = itemView.findViewById(R.id.browseTimeline);

        }

    }

}
