package com.example.faceapp.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faceapp.utilities.Constraints;
import com.example.faceapp.R;
import com.example.faceapp.model.User;
import com.example.faceapp.viewmodel.LoginViewModel;
import androidx.lifecycle.ViewModelProvider;

public class Log_in_page extends AppCompatActivity {
    private TextView usernameEx, passwordEx, userExEx;
    private EditText user_name, pass_word;
    private Constraints constraints;
    private LoginViewModel loginViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // Initialize the ViewModel
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.init(this);

        constraints = new Constraints();
        Button butLogin = findViewById(R.id.butLogin);
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

                // Input Validation
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

                // Login
                if (userCheck) {
                    loginViewModel.login(new User(username, password));
                    userExEx.setVisibility(View.GONE);
                }
                else{
                    userExEx.setText("Invalid username or password");
                    userExEx.setVisibility(View.VISIBLE);
                }
            }
        });

        loginViewModel.observeLoginResult().observe(this, loginSuccess -> {
            if (loginSuccess) {
                // Login successful, navigate to the next activity
                Toast.makeText(Log_in_page.this, "Login successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Log_in_page.this, Feed_page.class);
                startActivity(i);
            } else {
                // Login failed, show an error message
                Toast.makeText(Log_in_page.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });

        Button butForgot = findViewById(R.id.butForgot);
        butForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Log_in_page.this, "That's too bad man", Toast.LENGTH_SHORT).show();
            }
        });
        Button butSignup = findViewById(R.id.butSignup);
            butSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Log_in_page.this, Sign_up_page.class);
                    startActivity(i);
                }
            });
    }
}