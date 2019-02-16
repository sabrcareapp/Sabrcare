package com.sabrcare.app.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sabrcare.app.fragments.HomeFragment;
import com.sabrcare.app.fragments.MedicineFragment;
import com.sabrcare.app.R;
import com.sabrcare.app.fragments.RecordsFragment;
import com.sabrcare.app.fragments.SymptomTrackerFragment;
import com.sabrcare.app.fragments.TimelineFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity  {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;

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
        Fresco.initialize(this);
        setContentView(R.layout.activity_home);

        loadFragment(new HomeFragment());

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
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
}
