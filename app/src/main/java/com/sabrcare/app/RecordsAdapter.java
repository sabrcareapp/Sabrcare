package com.sabrcare.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.RecordsVH> {

    private ArrayList<String> folders;
    private Context context;


    public RecordsAdapter(ArrayList<String> folders, Context context) {
        this.folders = folders;
        this.context = context;
    }

    @NonNull
    @Override
    public RecordsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecordsVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecordsVH holder, int position) {
        holder.showFolder(folders.get(position));
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }

    class RecordsVH extends RecyclerView.ViewHolder{
        TextView foldername1;
        ImageView icon;

        public RecordsVH(@NonNull View itemView) {
            super(itemView);
            foldername1=itemView.findViewById(R.id.Foldername1);
            icon=itemView.findViewById(R.id.folderIc);
            icon.setColorFilter(Color.parseColor("#00574B"));
        }

        void showFolder(final String folderName){
            foldername1.setText(folderName);
            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent openFolder = new Intent(context,ReportFolderActivity.class);
                    openFolder.putExtra("folderName",folderName);
                    context.startActivity(openFolder);
                }
            });
        }
    }

}

