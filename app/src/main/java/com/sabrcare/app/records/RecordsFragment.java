package com.sabrcare.app.records;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionMenu;
import com.sabrcare.app.records.RecordsAdapter;
import com.sabrcare.app.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordsFragment extends Fragment {

    private ArrayList<String> records;
    private RecordsAdapter recordsAdapter;

    //boolean flag to know if main FAB is in open or closed state.
    private boolean fabExpanded = false;
    com.github.clans.fab.FloatingActionButton fabNewFolder;
    FloatingActionMenu materialDesignFAM;

    //Linear layout holding the Save submenu


    public RecordsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_records, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.RecordsRV);
        materialDesignFAM = view.findViewById(R.id.material_design_android_floating_action_menu);
        fabNewFolder = view.findViewById(R.id.NewFolderFAB);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        records = new ArrayList<>();
        records.add("Blood Tests");
        records.add("Biopsy");
        records.add("Insurance");
        records.add("CT Scans");
        records.add("Prescriptions");
        records.add("Doctor's Summary");
        records.add("Others");

        recordsAdapter = new RecordsAdapter(records,getContext());
        recyclerView.setAdapter(recordsAdapter);

        fabNewFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder newFolderDialogBuilder = new AlertDialog.Builder(getContext());
                newFolderDialogBuilder.setView(R.layout.fragment_new_folder);
                newFolderDialogBuilder.setCancelable(false);
                newFolderDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNegativeButton("Cancel",null);

                AlertDialog newFolderDialog = newFolderDialogBuilder.create();
                newFolderDialog.show();
            }
        });

        return view;
    }

}
