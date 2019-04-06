package com.sabrcare.app.tutorial;


import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sabrcare.app.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialFragment_3 extends Fragment {

    ImageView icon;

    public TutorialFragment_3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tutorial_3, container, false);
        icon = view.findViewById(R.id.img3);
        icon.setColorFilter(Color.parseColor("#2C8BBF"));
        return view;
    }

}
