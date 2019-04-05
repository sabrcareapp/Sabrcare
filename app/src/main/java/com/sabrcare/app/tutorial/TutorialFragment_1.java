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


public class TutorialFragment_1 extends Fragment {

    ImageView icon;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tutorial_1, container, false);
        icon = view.findViewById(R.id.img1);
        icon.setColorFilter(Color.parseColor("#2C8BBF"));
        return view;
    }
}
