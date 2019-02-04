package com.example.shahz.cricinfoadmin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shahz.cricinfoadmin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Alerts extends Fragment {


    public Alerts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_alerts, container, false);
        TextView textView=view.findViewById(R.id.textView);

        return view;
    }

}
