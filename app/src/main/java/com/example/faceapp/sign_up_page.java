package com.example.faceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class sign_up_page extends AppCompatActivity {
    private Button backBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        backBut = findViewById(R.id.backBut);
        backBut.setOnClickListener(v -> {
            Intent i = new Intent(sign_up_page.this, log_in_page.class);
            startActivity(i);
        });
    }

}