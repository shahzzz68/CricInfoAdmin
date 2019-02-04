package com.example.shahz.cricinfoadmin;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shahz.cricinfoadmin.Fragments.Alerts;
import com.example.shahz.cricinfoadmin.Fragments.Live_Scores;
import com.example.shahz.cricinfoadmin.Fragments.Matches;
import com.example.shahz.cricinfoadmin.Fragments.Players;
import com.example.shahz.cricinfoadmin.Fragments.Results;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class dashboard_admin extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {


    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView =findViewById(R.id.bottomNavView);


//        String string="this is a string";
//        String str=new String(string);
//        String newString=str.replace('g','s');
//        Toast.makeText(this, ""+newString, Toast.LENGTH_SHORT).show();


        Matches matches=new Matches();


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch(id)
        {
            case R.id.Matches:
                replaceFragment(new Matches());
                return true;
            case R.id.results:
                replaceFragment(new Results());
                break;
            case R.id.notification:
                replaceFragment(new Alerts());
                break;
            case R.id.live_scores:
                replaceFragment(new Live_Scores());
                break;
            case R.id.players:
                replaceFragment(new Players());
                break;
        }
        return true;
    }

    private void replaceFragment(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.contentFrame,fragment).commit();

    }
}
