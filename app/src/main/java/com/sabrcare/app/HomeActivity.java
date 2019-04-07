package com.sabrcare.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sabrcare.app.auth.SignInActivity;
import com.sabrcare.app.auth.SignUpActivity;
import com.sabrcare.app.medicine.MedicineFragment;
import com.sabrcare.app.records.RecordsFragment;
import com.sabrcare.app.symptomtracker.SymptomTrackerFragment;
import com.sabrcare.app.timeline.TimelineFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.MenuItem;

import java.util.Objects;

import static com.sabrcare.app.symptomtracker.SymptomAddActivity.flagIntent;

public class HomeActivity extends AppCompatActivity  {
    private static final int REQUEST_EXIT = 2;

    public static final String FILE="MyFile";
    SharedPreferences setting;
    int transfer;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    fragment = new HomeFragment();
//                    break;

                case R.id.navigation_timeline:
                    fragment = new TimelineFragment();
                    break;

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
        setContentView(R.layout.activity_home);
        //Fresco.initialize(this);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        transfer = getIntent().getIntExtra("transfer", 0);

        if(transfer==1){
            loadFragment(new RecordsFragment());
            navigation.setSelectedItemId(R.id.navigation_records);
        }else if(transfer==2){
            loadFragment(new SymptomTrackerFragment());
            navigation.setSelectedItemId(R.id.navigation_medicine);
        }else if(transfer==3){
            Log.e("<<<<<<<", "in 3");
            loadFragment(new SymptomTrackerFragment());
            navigation.setSelectedItemId(R.id.navigation_symptom_tracker);
        }

        setting= this.getSharedPreferences(FILE,MODE_PRIVATE);

        String token_value = setting.getString("Token","null");

        if(token_value.equals("null"))
        {
            Intent launchHome = new Intent(HomeActivity.this,LaunchActivity.class);
            startActivity(launchHome);
            finishAffinity();
            return ;
        }

        if(getIntent().getAction()!=null && getIntent().getAction().equalsIgnoreCase("updateMeds")){
            loadFragment(new MedicineFragment());
            navigation.setSelectedItemId(R.id.navigation_medicine);
        }
        else if(flagIntent==1){
            flagIntent=0;
            loadFragment(new SymptomTrackerFragment());
            navigation.setSelectedItemId(R.id.navigation_symptom_tracker);
        }
        else if (transfer != 0){
            if(transfer==1){
                loadFragment(new RecordsFragment());
                navigation.setSelectedItemId(R.id.navigation_records);
            }else if(transfer==2){
                loadFragment(new MedicineFragment());
                navigation.setSelectedItemId(R.id.navigation_medicine);
            }else if(transfer==3){
                //Log.e("<<<<<<<", "in 3");
                loadFragment(new SymptomTrackerFragment());
                navigation.setSelectedItemId(R.id.navigation_symptom_tracker);
            } //change this back to homefragment
        }
        else{
            loadFragment(new MedicineFragment());
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
