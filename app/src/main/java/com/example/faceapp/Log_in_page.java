package com.example.faceapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class Log_in_page extends AppCompatActivity {
    private TextView usernameEx, passwordEx, userExEx;
    private EditText user_name, pass_word;
    private Button butLogin, butSignup, butForgot;
    private Constraints constraints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        constraints = new Constraints();
        butLogin = findViewById(R.id.butLogin);
        usernameEx = findViewById(R.id.usernameException);
        passwordEx = findViewById(R.id.passwordException);
        userExEx = findViewById(R.id.userExistenceException);
        user_name = findViewById(R.id.username);
        pass_word = findViewById(R.id.password);
        usernameEx.setVisibility(View.GONE);
        passwordEx.setVisibility(View.GONE);
        userExEx.setVisibility(View.GONE);
        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Boolean userCheck = true;

                String username = user_name.getText().toString();
                String password = pass_word.getText().toString();
                try {
                    constraints.usernameCheck(username);
                    usernameEx.setVisibility(View.GONE);
                } catch (IllegalArgumentException e) {
                    usernameEx.setText(e.getMessage());
                    usernameEx.setVisibility(View.VISIBLE);
                    userCheck = false;
                }
                try {
                    constraints.passwordCheck(password);
                    passwordEx.setVisibility(View.GONE);
                } catch (Exception e) {
                    passwordEx.setText(e.getMessage());
                    passwordEx.setVisibility(View.VISIBLE);
                    userCheck = false;
                }
                try {
                    if (!username.equals("Admin@gmail.com") || !password.equals("a12345678"))
                        throw new IllegalArgumentException("User doesn't exist or the credentials are wrong.");
                    userExEx.setVisibility(View.GONE);
                } catch (IllegalArgumentException e) {
                    userExEx.setText("Invalid username or password");
                    userExEx.setVisibility(View.VISIBLE);
                    userCheck = false;
                }
                if (userCheck) {
                    Toast.makeText(Log_in_page.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Log_in_page.this, Feed_page.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Log_in_page.this, "Login failed", Toast.LENGTH_SHORT).show();

                }
            }
        });

        butForgot = findViewById(R.id.butForgot);
        butForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Log_in_page.this, "That's to bad man", Toast.LENGTH_SHORT).show();
            }
        });
            butSignup = findViewById(R.id.butSignup);
            butSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Log_in_page.this, Sign_up_page.class);
                    startActivity(i);
                }
            });

    }
}