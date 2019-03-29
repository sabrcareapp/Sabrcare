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
                case "None": holder.none1.setChecked(true);
                             break;
                case "Mild": holder.mild.setChecked(true);
                             break;
                case "Moderate": holder.moderate.setChecked(true);
                             break;
                case "Severe": holder.severe.setChecked(true);
                             break;
//                case "unbearable": holder.unbearable.setChecked(true);
//                             break;
            }
        }

        holder.adSymptom(symptoms.get(c).name);
        holder.none1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.onRadioButtonClicked(view);
                int i=0;
                for(;i<73;i++){
                    if (holder.symptomname1.getText().equals(symptoms.get(i).name))
                        break;
                }
                symptoms.get(i).severity="None";
                //Log.e("<<<<<", ""+i);
            }
        });
        holder.mild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.onRadioButtonClicked(view);
                int i=0;
                for(;i<73;i++){
                    if (holder.symptomname1.getText().equals(symptoms.get(i).name))
                        break;
                }
                symptoms.get(i).severity="Mild";
            }
        });
        holder.moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.onRadioButtonClicked(view);
                int i=0;
                for(;i<73;i++){
                    if (holder.symptomname1.getText().equals(symptoms.get(i).name))
                        break;
                }
                symptoms.get(i).severity="Moderate";
            }
        });
        holder.severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.onRadioButtonClicked(view);
                int i=0;
                for(;i<73;i++){
                    if (holder.symptomname1.getText().equals(symptoms.get(i).name))
                        break;
                }
                symptoms.get(i).severity="Severe";
            }
        });
//        holder.unbearable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                holder.onRadioButtonClicked(view);
//                symptoms.get(a.get(position)).severity=s;
//            }
//        });
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
        }

        private void adSymptom(final String symptom){
            symptomname1.setText(symptom);
        }

        private void onRadioButtonClicked(View view){
            boolean checked = ((RadioButton) view).isChecked();
            switch (view.getId()){
                case R.id.none: if(checked){
                                    s = "None";
                                }
                                break;
                case R.id.mild: if(checked){
                                    s = "Mild";
                                }
                                break;
                case R.id.moderate: if(checked){
                                    s = "Moderate";
                                }
                                break;
                case R.id.severe: if(checked){
                                        s = "Severe";
                                    }
                                    break;
//                case R.id.unbearable: if(checked){
//                                        c=1;
//                                        s = "unbearable";
//                                    }
//                                    break;
            }
        }

    }

}
