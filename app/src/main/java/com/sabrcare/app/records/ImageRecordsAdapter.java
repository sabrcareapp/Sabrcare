package com.sabrcare.app.records;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sabrcare.app.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageRecordsAdapter extends RecyclerView.Adapter<ImageRecordsAdapter.ImageRecordsVH> {

    private ArrayList<String> files;
    private ArrayList<String> fileURLs;
    private Context context;

    public ImageRecordsAdapter(ArrayList<String> files, ArrayList<String> fileURLs, Context context) {
        this.files = files;
        this.fileURLs = fileURLs;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageRecordsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageRecordsVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_record,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ImageRecordsVH holder, int position) {
        holder.populateFile(files.get(position),fileURLs.get(position));
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    class ImageRecordsVH extends RecyclerView.ViewHolder{
        TextView fileName;
        ImageView fileImage;

        public ImageRecordsVH(@NonNull View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.imgfilename);
            fileImage = itemView.findViewById(R.id.imageFile);
        }

        void populateFile(String name,String Url){
            fileName.setText(name);
            Glide.with(context).load(Url).into(fileImage);
        }
    }
}
