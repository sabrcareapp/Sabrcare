package com.sabrcare.app.symptomtracker;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sabrcare.app.R;
import com.sabrcare.app.symptomtracker.SymptomAdapter;
import com.sabrcare.app.symptomtracker.SymptomAddActivity;

import java.text.DateFormat;

public class SymptomTrackerFragment extends Fragment {

    public int flag=0;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        return inflater.inflate(R.layout.fragment_symptom_tracker, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        RecyclerView sytRv = view.findViewById(R.id.syt_rv);

        sytRv.setLayoutManager(new LinearLayoutManager(getContext()));
        SymptomAdapter symptomAdapter = new SymptomAdapter();
        sytRv.setAdapter(symptomAdapter);

        TextView sytDate = view.findViewById(R.id.syt_date);
        Button sytBtn = view.findViewById(R.id.syt_btn);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new java.util.Date());
        sytDate.setText(currentDateTimeString);

        com.github.clans.fab.FloatingActionButton sytFab = view.findViewById(R.id.syt_fab);
        sytFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=1;
                Intent intent = new Intent(getActivity(), SymptomAddActivity.class);
                startActivity(intent);
            }
        });
    }
}