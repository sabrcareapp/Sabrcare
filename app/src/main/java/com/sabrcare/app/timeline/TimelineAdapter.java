package com.sabrcare.app.timeline;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.sabrcare.app.HomeActivity;
import com.sabrcare.app.ModelTimeline;
import com.sabrcare.app.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineVH> {

    public Context ctx;
    private ArrayList<ModelTimeline> timeline;
    private RequestOptions options;

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
        holder.date.setText(timeline.get(position).getDate());
        holder.time.setText(timeline.get(position).getTime());

        if(timeline.get(position).getTimelineType().equals("Record")) {
            holder.pic.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_records_image));
            holder.pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("<<<<<<", "in pic");
                    Intent intent = new Intent(ctx, HomeActivity.class);
                    intent.putExtra("transfer", 1);
                    ctx.startActivity(intent);
                }
            });
            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("<<<<<<", "in pic");
                    Intent intent = new Intent(ctx, HomeActivity.class);
                    intent.putExtra("transfer", 1);
                    ctx.startActivity(intent);
                }
            });
            holder.subtitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("<<<<<<", "in pic");
                    Intent intent = new Intent(ctx, HomeActivity.class);
                    intent.putExtra("transfer", 1);
                    ctx.startActivity(intent);
                }
            });
            holder.browseTimeline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("<<<<<<", "in pic");
                    Intent intent = new Intent(ctx, HomeActivity.class);
                    intent.putExtra("transfer", 1);
                    ctx.startActivity(intent);
                }
            });
        }
        else if(timeline.get(position).getTimelineType().equals("Medicine")) {
            holder.pic.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_pills_solid));
            holder.pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("<<<<<<", "in pic");
                    Intent intent = new Intent(ctx, HomeActivity.class);
                    intent.putExtra("transfer", 2);
                    ctx.startActivity(intent);
                }
            });
            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("<<<<<<", "in pic");
                    Intent intent = new Intent(ctx, HomeActivity.class);
                    intent.putExtra("transfer", 2);
                    ctx.startActivity(intent);
                }
            });
            holder.subtitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("<<<<<<", "in pic");
                    Intent intent = new Intent(ctx, HomeActivity.class);
                    intent.putExtra("transfer", 2);
                    ctx.startActivity(intent);
                }
            });
            holder.browseTimeline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("<<<<<<", "in pic");
                    Intent intent = new Intent(ctx, HomeActivity.class);
                    intent.putExtra("transfer", 2);
                    ctx.startActivity(intent);
                }
            });
        }
        else if(timeline.get(position).getTimelineType().equals("Symptom")) {
            holder.pic.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_stethoscope_solid));
            holder.pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("<<<<<<", "in pic");
                    Intent intent = new Intent(ctx, HomeActivity.class);
                    intent.putExtra("transfer", 3);
                    ctx.startActivity(intent);
                }
            });
            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("<<<<<<", "in pic");
                    Intent intent = new Intent(ctx, HomeActivity.class);
                    intent.putExtra("transfer", 3);
                    ctx.startActivity(intent);
                }
            });
            holder.subtitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("<<<<<<", "in pic");
                    Intent intent = new Intent(ctx, HomeActivity.class);
                    intent.putExtra("transfer", 3);
                    ctx.startActivity(intent);
                }
            });
            holder.browseTimeline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("<<<<<<", "in pic");
                    Intent intent = new Intent(ctx, HomeActivity.class);
                    intent.putExtra("transfer", 3);
                    ctx.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return timeline.size();
    }

    public class TimelineVH extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView subtitle;
        public TextView date;
        public TextView time;
        public ImageView pic;
        public Button browseTimeline;//will be changed to using Fresco library later

        public TimelineVH(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            subtitle=itemView.findViewById(R.id.subtitle);
            pic=itemView.findViewById(R.id.pic);
            browseTimeline = itemView.findViewById(R.id.browseTimeline);
            time = itemView.findViewById(R.id.time);
            date=itemView.findViewById(R.id.date);
        }

    }
/*
    public boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private FragmentManager getSupportFragmentManager() {
    }
*/
}
