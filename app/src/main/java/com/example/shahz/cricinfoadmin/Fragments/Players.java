package com.example.shahz.cricinfoadmin.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shahz.cricinfoadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Players extends Fragment {

    private Button btn,clearBtn;
    private DocumentReference documentReference;
    private FirebaseFirestore firestore;
    private Spinner spinner;
    private EditText playerName;
    private EditText playerType;
    private EditText playerStyle;
    private EditText playerTeam;

    String PlayerName;
    String PlayerType;
    String PlayerStyle;
    String PlayerTeam;

    List<String> documentList;
    View rootView;

    public Players() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_players, container, false);

        rootView=view;

        firestore = FirebaseFirestore.getInstance();
        btn = view.findViewById(R.id.button);
        clearBtn=view.findViewById(R.id.clearBtn);

        spinner=view.findViewById(R.id.spinner);

        playerName=view.findViewById(R.id.NameEditText);
        playerType=view.findViewById(R.id.TypeEditText);
        playerStyle=view.findViewById(R.id.StyleEditText);
        playerTeam=view.findViewById(R.id.TeamEditText);


        DocumentList();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerName=playerName.getText().toString();
                PlayerType=playerType.getText().toString();
                PlayerStyle=playerStyle.getText().toString();
                PlayerTeam=playerTeam.getText().toString();

//
//                //String matchId=match1.getText().toString()+match2.getText().toString()+date.getText().toString();
//
                Map<String, String> depts = new HashMap<>();
                depts.put("deptName", PlayerTeam);

                Map<String, String> userMap = new HashMap<>();
                userMap.put("name", PlayerName);
                userMap.put("style", PlayerStyle);
                userMap.put("type", PlayerType);
                userMap.put("team", PlayerTeam);


                documentReference = firestore.collection("Players").document(PlayerTeam);

                documentReference.set(depts);

                firestore.collection("Players").document(PlayerTeam)
                         .collection("player details").document(PlayerName)
                         .set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Database Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playerTeam.setText("");
                playerName.setText("");
                playerStyle.setText("");
                playerType.setText("");

            }
        });

        return view;
    }


    ////////////RETRIEVE LIST OF DOCUMENTS(TEAM NAMES)//////////////////
    private void DocumentList()
    {
        documentList=new ArrayList<>();
        firestore.collection("Players").orderBy("deptName").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {

                        documentList.add(document.getId());

                    }
                    documentList.add(0,"Choose team");

                    /////////////   SPINNER CALL/////////////
                    Spinner(documentList);
                    ///////////////////////////////////////////
                } else {
                    Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Spinner(List<String> list)
    {
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(rootView.getContext(),
                android.R.layout.simple_spinner_item, list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i>0)
                {
                    String text= spinner.getSelectedItem().toString();
                    playerTeam.setText(text);
                    Toast.makeText(adapterView.getContext(),text, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });


    }



}
