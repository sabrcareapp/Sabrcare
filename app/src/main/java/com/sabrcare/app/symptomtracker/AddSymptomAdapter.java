package com.sabrcare.app.symptomtracker;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sabrcare.app.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;

import static com.sabrcare.app.symptomtracker.SymptomAddActivity.symptoms;

public class AddSymptomAdapter extends RecyclerView.Adapter<AddSymptomAdapter.AddSymptomVH> {

    private Realm db = Realm.getDefaultInstance();
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
        else if(("sytaRv7").equals(x)){
            i=34;
        }
        else if(("sytaRv8").equals(x)){
            i=44;
        }
        else if(("sytaRv9").equals(x)){
            i=58;
        }
        else if(("sytaRv10").equals(x)){
            i=64;
        }
        else if(("sytaRv11").equals(x)){
            i=69;
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
        //Log.e("imp <<<<", symptoms.get(4).getName()+"");
        holder.addSymptom(symptoms.get(position+i).getName());
        if(symptoms.get(position+i).getIsCheck()==1){
            holder.checkBox.setChecked(true);
        }
        else{
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked()){
                    final int finalI = position+i;
                    db.executeTransaction(
                            new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    symptoms.get(finalI).setIsCheck(1);
                                    db.insertOrUpdate(symptoms.get(finalI));
                                }
                            }
                    );
                }
                else{
                    final int finalI = position+i;
                    db.executeTransaction(
                            new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    symptoms.get(finalI).setIsCheck(0);
                                    db.insertOrUpdate(symptoms.get(finalI));
                                }
                            }
                    );
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
        else if(i==34){
            return 10;
        }
        else if(i==44){
            return 14;
        }
        else if(i==58){
            return 6;
        }
        else if(i==64){
            return 5;
        }
        else if(i==69){
            return 5;
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
