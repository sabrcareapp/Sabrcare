package com.sabrcare.app.symptomtracker;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sabrcare.app.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.sabrcare.app.symptomtracker.SymptomAddActivity.symptoms;

public class SymptomAdapter extends RecyclerView.Adapter<SymptomAdapter.SymptomVH> {

    public static String s;
    private int c=0;
    private ArrayList<Integer> a=new ArrayList<>(0);

    @NonNull
    @Override
    public SymptomAdapter.SymptomVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SymptomAdapter.SymptomVH(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_symptom,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final SymptomAdapter.SymptomVH holder, final int position) {

        while(symptoms.get(c).isCheck!=1){
            Log.e(TAG, "x1"+c);
            c++;
        }
        a.add(position, c);

        if(!(symptoms.get(c).severity.equals("null"))){
            switch(symptoms.get(c).severity){
                case "none": holder.none1.setChecked(true);
                             break;
                case "mild": holder.mild.setChecked(true);
                             break;
                case "moderate": holder.moderate.setChecked(true);
                             break;
                case "severe": holder.severe.setChecked(true);
                             break;
                case "unbearable": holder.unbearable.setChecked(true);
                             break;
            }
        }

        holder.adSymptom(symptoms.get(c).name);
        holder.none1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.onRadioButtonClicked(view);
                symptoms.get(a.get(position)).severity=s;
            }
        });
        holder.mild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.onRadioButtonClicked(view);
                symptoms.get(a.get(position)).severity=s;
            }
        });
        holder.moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.onRadioButtonClicked(view);
                symptoms.get(a.get(position)).severity=s;
            }
        });
        holder.severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.onRadioButtonClicked(view);
                symptoms.get(a.get(position)).severity=s;
            }
        });
        holder.unbearable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.onRadioButtonClicked(view);
                symptoms.get(a.get(position)).severity=s;
            }
        });
        c++;
    }


    @Override
    public int getItemCount() {
        int x=0;
        if(symptoms.size()!=0) {
            for (int i = 0; i < 74; i++) {
                if (symptoms.get(i).isCheck == 1)
                    x++;
            }
        }
        return x;
    }

    class SymptomVH extends RecyclerView.ViewHolder{
        private TextView symptomname1;
        private RadioGroup radioGroup;
        private RadioButton none1, mild, moderate, severe, unbearable;

        SymptomVH(@NonNull View itemView) {
            super(itemView);
            symptomname1 = itemView.findViewById(R.id.title);
            radioGroup = itemView.findViewById(R.id.radio);
            none1 = itemView.findViewById(R.id.none);
            mild = itemView.findViewById(R.id.mild);
            moderate = itemView.findViewById(R.id.moderate);
            severe = itemView.findViewById(R.id.severe);
            unbearable = itemView.findViewById(R.id.unbearable);
        }

        private void adSymptom(final String symptom){
            symptomname1.setText(symptom);
        }

        private void onRadioButtonClicked(View view){
            boolean checked = ((RadioButton) view).isChecked();
            switch (view.getId()){
                case R.id.none: if(checked){
                                    c=1;
                                    s = "none";
                                }
                                break;
                case R.id.mild: if(checked){
                                    c=1;
                                    s = "mild";
                                }
                                break;
                case R.id.moderate: if(checked){
                                    c=1;
                                    s = "moderate";
                                }
                                break;
                case R.id.severe: if(checked){
                                        c=1;
                                        s = "severe";
                                    }
                                    break;
                case R.id.unbearable: if(checked){
                                        c=1;
                                        s = "unbearable";
                                    }
                                    break;
            }
        }

    }

}
