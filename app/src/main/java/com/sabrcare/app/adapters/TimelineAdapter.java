package com.sabrcare.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sabrcare.app.R;
import com.sabrcare.app.models.TimelineModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineVH> {

    private ArrayList<TimelineModel> timeline;
    public Context ctx;
    public TimelineAdapter(ArrayList<TimelineModel> timeline,Context ctx) {


        this.ctx=ctx;
        this.timeline=timeline;
    }

    @NonNull
    @Override
    public TimelineVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TimelineAdapter.TimelineVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineVH holder, int position) {
        holder.title.setText(timeline.get(position).getTitle());
        holder.subtitle.setText(timeline.get(position).getSubtitle());
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
}
