package com.example.faceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Sign_up_page extends AppCompatActivity {
    private Button backBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        EmailFrag1 email_fragment1 = new EmailFrag1();

        /*backBut = findViewById(R.id.backBut);
        backBut.setOnClickListener(v -> {
            Intent i = new Intent(Sign_up_page.this, Log_in_page.class);
            startActivity(i);
        });*/
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragmentContainerView, EmailFrag1.class, null).setReorderingAllowed(true)
                .addToBackStack("name")
            .commit();
        }
    }