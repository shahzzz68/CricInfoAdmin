package com.example.shahz.cricinfoadmin.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shahz.cricinfoadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Matches extends Fragment {


    FirebaseFirestore firestore;
    EditText match1,match2,date,time,description;
    Button btn;
    String dateString;


    public Matches() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.matches, container, false);

        match1=view.findViewById(R.id.match1);
        match2=view.findViewById(R.id.match2);
        date=view.findViewById(R.id.matchDate);
        time=view.findViewById(R.id.matchTime);
        description=view.findViewById(R.id.desc);
        btn=view.findViewById(R.id.button);

        firestore=FirebaseFirestore.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String matchId=match1.getText().toString()+match2.getText().toString()+date.getText().toString();

                Map<String, String> userMap = new HashMap<>();
                userMap.put("team1", match1.getText().toString());
                userMap.put("team2", match2.getText().toString());
                userMap.put("date", date.getText().toString().trim());
                userMap.put("time", time.getText().toString());
                userMap.put("description", description.getText().toString());

                firestore.collection("Matches").document(matchId).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Database Error: "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        return view;

    }

}
