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

    private int i;

    public AddSymptomAdapter(String x){
        if(("sytaRv").equals(x)){
            i=0;
        }
        else if(("sytaRv1").equals(x)){
            i=9;
        }
        else if(("sytaRv2").equals(x)){
            i=11;
        }
        else if(("sytaRv3").equals(x)){
            i=18;
        }
        else if(("sytaRv4").equals(x)){
            i=22;
        }
        else if(("sytaRv5").equals(x)){
            i=28;
        }
        else if(("sytaRv6").equals(x)){
            i=32;
        }
    }

    @NonNull
    @Override
    public AddSymptomAdapter.AddSymptomVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddSymptomAdapter.AddSymptomVH(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_add_symptom,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final AddSymptomAdapter.AddSymptomVH holder, final int position) {
        holder.addSymptom(symptoms.get(position+i).name);
        if(symptoms.get(position+i).isCheck==1){
            holder.checkBox.setChecked(true);
        }
        else{
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked()){
                    symptoms.get(position+i).isCheck=1;
                }
                else{
                    symptoms.get(position+i).isCheck=0;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(i==0){
            return 9;
        }
        else if(i==9){
            return 2;
        }
        else if(i==11){
            return 7;
        }
        else if(i==18){
            return 4;
        }
        else if(i==22){
            return 6;
        }
        else if(i==28){
            return 4;
        }
        else if(i==32){
            return 2;
        }
        else
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
