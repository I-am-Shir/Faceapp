package com.example.faceapp;

import static android.text.TextUtils.replace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class sign_up_page extends AppCompatActivity {
    private Button backBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_email1);

        emailFragment1 email_fragment1 = new emailFragment1();

        backBut = findViewById(R.id.backBut);
        backBut.setOnClickListener(v -> {
            Intent i = new Intent(sign_up_page.this, log_in_page.class);
            startActivity(i);
        });
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragmentContainerView, emailFragment1.class, null).setReorderingAllowed(true)
                .addToBackStack("name")
            .commit();
        }
    }