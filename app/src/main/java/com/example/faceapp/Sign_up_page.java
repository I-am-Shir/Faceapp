package com.example.faceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Sign_up_page extends AppCompatActivity {
    private Button backBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_email1);

        EmailFragment1 email_fragment1 = new EmailFragment1();

        backBut = findViewById(R.id.backBut);
        backBut.setOnClickListener(v -> {
            Intent i = new Intent(Sign_up_page.this, Log_in_page.class);
            startActivity(i);
        });
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragmentContainerView, EmailFragment1.class, null).setReorderingAllowed(true)
                .addToBackStack("name")
            .commit();
        }
    }