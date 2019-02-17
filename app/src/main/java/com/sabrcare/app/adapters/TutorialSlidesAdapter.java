package com.sabrcare.app.adapters;

import android.content.Context;

import com.sabrcare.app.fragments.TutorialFragment_1;
import com.sabrcare.app.fragments.TutorialFragment_2;
import com.sabrcare.app.fragments.TutorialFragment_3;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


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
