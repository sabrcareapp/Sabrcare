package com.sabrcare.app.medicine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.github.clans.fab.FloatingActionMenu;
import com.sabrcare.app.R;
import com.sabrcare.app.activities.ProfileActivity;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class MedicineFragment extends Fragment {

    public com.github.clans.fab.FloatingActionButton fabNewMed;
    public FloatingActionMenu materialDesignFAM;
    private Realm realm;
    private MedicineAdapter medicineAdapter;

    Button profile;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.medicine_rv);
        realm=Realm.getDefaultInstance();
        materialDesignFAM = view.findViewById(R.id.material_design_android_floating_action_menu);
        fabNewMed = view.findViewById(R.id.NewMedicineFAB);


        Toolbar medicine_toolbar = view.findViewById(R.id.medicine_toolbar);
        medicine_toolbar.setTitle("");
        ((AppCompatActivity)getActivity()).setSupportActionBar(medicine_toolbar);

        //TODO This data to be loaded from server.

        RealmResults<MedicineModel> medicineModels = realm.where(MedicineModel.class).findAll();
        ArrayList<MedicineModel> medicineModelArrayList = new ArrayList<>(medicineModels);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        medicineAdapter = new MedicineAdapter(medicineModelArrayList, getContext());
        recyclerView.setAdapter(medicineAdapter);

        profile = view.findViewById(R.id.profile);


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loadprofile = new Intent(getContext(),ProfileActivity.class);
                getContext().startActivity(loadprofile);

            }
        });


        fabNewMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newMed = new Intent(getActivity(), NewMedActivity.class);
                newMed.setAction("New");
                startActivity(newMed);
            }
        });
        return view;
    }

}
