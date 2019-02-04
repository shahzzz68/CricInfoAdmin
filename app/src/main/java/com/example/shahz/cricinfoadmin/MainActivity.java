package com.example.shahz.cricinfoadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView loginBtn;
    EditText emailLogin, passLogin;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();
        emailLogin=findViewById(R.id.email_Login);
        passLogin=findViewById(R.id.password_Login);



        loginBtn= findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=emailLogin.getText().toString();
                String pass=passLogin.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass))
                {
                  //  progressBar.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                startActivity(new Intent(MainActivity.this,dashboard_admin.class));
                                finish();

                            }
                            else
                            {
                                String s = task.getException().getMessage();
                                Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                            }

                           // progressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                }
                else if (TextUtils.isEmpty(email))
                {
                    emailLogin.setError("email required");

                }
                else
                {
                    passLogin.setError("password required");
                }



            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser =auth.getCurrentUser();
        if (currentUser != null)
        {
            sendToMain();

        }
    }

    private void sendToMain()
    {
        Intent intent =new Intent(MainActivity.this, Dashboard.class);
        startActivity(intent);
        finish();
    }
}
