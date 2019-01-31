package com.sabrcare.app;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        ViewPager pager =  findViewById(R.id.TutorialViewPager);
        TabLayout tabLayout =  findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        TutorialSlidesAdapter fragAdapter = new TutorialSlidesAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        pager.setAdapter(fragAdapter);
        tabLayout.setupWithViewPager(pager, true);

    }
}
