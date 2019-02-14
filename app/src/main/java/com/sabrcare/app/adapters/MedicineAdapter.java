package com.sabrcare.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sabrcare.app.R;
import com.sabrcare.app.models.MedicineModel;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineVH> {

    private ArrayList<MedicineModel> medicine;
    public Context ctx;
    public MedicineAdapter(ArrayList<MedicineModel> medicine, Context ctx) {


        this.ctx=ctx;
        this.medicine=medicine;
    }

    @NonNull
    @Override
    public MedicineAdapter.MedicineVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicineAdapter.MedicineVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicine,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineVH holder, int position) {
        holder.med_name.setText(medicine.get(position).getMed_name());
        holder.day_phase.setText(medicine.get(position).getDay_phase());
        holder.reminder.setText(medicine.get(position).getReminder());
        holder.time.setText(medicine.get(position).getTime());
    }



    @Override
    public int getItemCount() {
        return medicine.size();
    }

    public class MedicineVH extends RecyclerView.ViewHolder {

        public TextView med_name;
        public TextView day_phase;
        public TextView reminder;
        public TextView time;
        public MedicineVH(@NonNull View itemView) {
            super(itemView);
            med_name=itemView.findViewById(R.id.med_name);
            day_phase=itemView.findViewById(R.id.day_phase);
            reminder=itemView.findViewById(R.id.reminder);
            time=itemView.findViewById(R.id.time);

        }

    }
}
