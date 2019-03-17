package com.sabrcare.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sabrcare.app.medicine.MedicineFragment;
import com.sabrcare.app.records.RecordsFragment;
import com.sabrcare.app.symptomtracker.SymptomTrackerFragment;
import com.sabrcare.app.timeline.TimelineFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;

import java.util.Objects;

import static com.sabrcare.app.symptomtracker.SymptomAddActivity.flag;

public class HomeActivity extends AppCompatActivity  {
    private static final int REQUEST_EXIT = 2;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    fragment = new HomeFragment();
//                    break;
//
//                case R.id.navigation_timeline:
//                    fragment = new TimelineFragment();
//                    break;

                case R.id.navigation_medicine:
                    fragment = new MedicineFragment();
                    break;

                case R.id.navigation_records:
                    fragment= new RecordsFragment();
                    break;

                case R.id.navigation_symptom_tracker:
                    fragment = new SymptomTrackerFragment();
            }

            return loadFragment(fragment);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_home);

       // String username = Objects.requireNonNull(getIntent().getExtras()).get("username").toString();



        BottomNavigationView navigation = findViewById(R.id.navigation);
        if(getIntent().getAction()!=null && getIntent().getAction().equalsIgnoreCase("updateMeds")){
            loadFragment(new MedicineFragment());
            navigation.setSelectedItemId(R.id.navigation_medicine);
        }
        else if(flag==1){
            flag=0;
            loadFragment(new SymptomTrackerFragment());
            navigation.setSelectedItemId(R.id.navigation_symptom_tracker);
        }
        else {
            loadFragment(new MedicineFragment());     //change this back to homefragment
        }

//        mTextMessage = (TextView) findViewById(R.id.message);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.app_name);
        builder.setMessage("Really Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", null);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
