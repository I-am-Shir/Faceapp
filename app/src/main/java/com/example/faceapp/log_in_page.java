package com.example.faceapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class log_in_page extends AppCompatActivity {
    private EditText user_name, pass_word;
    private Button butLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        butLogin = findViewById(R.id.butLogin);
        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                user_name = findViewById(R.id.username);
                pass_word = findViewById(R.id.password);
                String username = user_name.getText().toString();
                String password = pass_word.getText().toString();

                // Implement authentication logic here
                if (username.equals("Admin") && password.equals("123")) {
                    // Successful login
                    Toast.makeText(log_in_page.this, "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    // Failed login
                    Toast.makeText(log_in_page.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(log_in_page.this, feed_page.class);
                    startActivity(i);
                }
            }
        });

            Button butSignup = findViewById(R.id.butSignup);
            butSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(log_in_page.this, sign_up_page.class);
                    startActivity(i);
                }
            });

    }
}