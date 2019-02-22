package com.sabrcare.app.symptomtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sabrcare.app.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.sabrcare.app.symptomtracker.SymptomAddActivity.symptoms;

public class AddSymptomAdapter extends RecyclerView.Adapter<AddSymptomAdapter.AddSymptomVH> {

    @NonNull
    @Override
    public AddSymptomAdapter.AddSymptomVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddSymptomAdapter.AddSymptomVH(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_add_symptom,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final AddSymptomAdapter.AddSymptomVH holder, final int position) {
        holder.addSymptom(symptoms.get(position).name);
        if(symptoms.get(position).isCheck==1){
            holder.checkBox.setChecked(true);
        }
        else{
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked()){
                    symptoms.get(position).isCheck=1;
                }
                else{
                    symptoms.get(position).isCheck=0;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return symptoms.size();
    }

    class AddSymptomVH extends RecyclerView.ViewHolder{
        private TextView symptomname1;
        private CheckBox checkBox;

        AddSymptomVH(@NonNull View itemView) {
            super(itemView);
            symptomname1 = itemView.findViewById(R.id.title);
            checkBox = itemView.findViewById(R.id.checkbox);
        }

        private void addSymptom(final String symptom){
            symptomname1.setText(symptom);
        }
    }
}
