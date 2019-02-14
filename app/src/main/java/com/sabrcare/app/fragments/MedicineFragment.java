package com.sabrcare.app.fragments;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sabrcare.app.R;
import com.sabrcare.app.adapters.MedicineAdapter;
import com.sabrcare.app.models.MedicineModel;

import java.util.ArrayList;


public class MedicineFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_medicine, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.medicine_rv);



        ArrayList<MedicineModel> medicine = new ArrayList<>();

        //generate 10 items just for now.WIll be changed later
        for(int i=0;i<10;i++) {
            medicine.add(new MedicineModel("Paracetamol", "Evening","REMINDER","12:00"));
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MedicineAdapter medicineAdapter = new MedicineAdapter(medicine,getContext());
        recyclerView.setAdapter(medicineAdapter);
        return view;
    }
    }
