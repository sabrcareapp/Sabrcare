package com.sabrcare.app;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TutorialSlidesAdapter extends FragmentPagerAdapter {

    private Context context;
    private int totalTabs;

    public TutorialSlidesAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0 :
                return new TutorialFragment_1();

            case 1 :
                return new TutorialFragment_2();

            case 2:
                return new TutorialFragment_3();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
