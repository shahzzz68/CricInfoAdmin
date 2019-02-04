package com.example.shahz.cricinfoadmin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.shahz.cricinfoadmin.Fragments.Alerts;
import com.example.shahz.cricinfoadmin.Fragments.Live_Scores;
import com.example.shahz.cricinfoadmin.Fragments.Matches;
import com.example.shahz.cricinfoadmin.Fragments.Players;
import com.example.shahz.cricinfoadmin.Fragments.Results;

public class Dashboard extends AppCompatActivity {

    private TextView mTextMessage;
    private Toolbar toolbar;
    private CharSequence toolbarText;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment=null;

            switch (item.getItemId()) {
                case R.id.Matches:
                   selectedFragment=new Matches();
                   toolbarText="Matches";
                    break;
                case R.id.players:
                    selectedFragment=new Players();
                    break;
                case R.id.live_scores:
                    selectedFragment=new Live_Scores();
                    break;
                case R.id.notification:
                    selectedFragment=new Alerts();
                    break;
                case R.id.results:
                    selectedFragment=new Results();
                     break;
            }

            replaceFragment(selectedFragment);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(toolbarText);
        setSupportActionBar(toolbar);


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        replaceFragment(new Matches());
    }


    private void replaceFragment(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();

    }

}
