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

public class FileRecordsAdapter extends RecyclerView.Adapter<FileRecordsAdapter.FileRecordsVH> {

    private ArrayList<String> FilesList;
    Context context;

    public FileRecordsAdapter(ArrayList<String> filesList, Context context) {
        FilesList = filesList;
        this.context = context;
    }

    @NonNull
    @Override
    public FileRecordsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FileRecordsVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file_record,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FileRecordsVH holder, int position) {
        holder.setFile(FilesList.get(position));
    }

    @Override
    public int getItemCount() {
        return FilesList.size();
    }

    class FileRecordsVH extends RecyclerView.ViewHolder{
        TextView filename;
        public FileRecordsVH(@NonNull View itemView) {
            super(itemView);
            filename = itemView.findViewById(R.id.fileNameTV);
        }

        void setFile(String name){
            filename.setText(name);
        }
    }
}
