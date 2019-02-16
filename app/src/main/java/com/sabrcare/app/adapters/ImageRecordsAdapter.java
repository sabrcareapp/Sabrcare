package com.sabrcare.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sabrcare.app.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageRecordsAdapter extends RecyclerView.Adapter<ImageRecordsAdapter.ImageRecordsVH> {

    ArrayList<String> files;
    Context context;

    public ImageRecordsAdapter(ArrayList<String> files, Context context) {
        this.files = files;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageRecordsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageRecordsVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_record,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ImageRecordsVH holder, int position) {
        holder.populateFile(files.get(position));
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    class ImageRecordsVH extends RecyclerView.ViewHolder{
        TextView fileName;
        public ImageRecordsVH(@NonNull View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.imgfilename);
        }

        void populateFile(String name){
            fileName.setText(name);
        }
    }
}
