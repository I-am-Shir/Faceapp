package com.example.faceapp.view.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faceapp.R;
import com.example.faceapp.utilities.Constraints;
import com.example.faceapp.view.Log_in_page;
import com.example.faceapp.view.Sign_up_page;

public class EmailFrag1 extends Fragment {
    private Constraints constraints;
    private Button nextBut, backBut;
    private EditText email;
    private TextView emailEx;
    private Boolean emailCheck;
    public EmailFrag1() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_email1, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        constraints = new Constraints();
        nextBut = view.findViewById(R.id.nextBut);
        backBut = view.findViewById(R.id.backBut);
        email = view.findViewById(R.id.editTextEmail);
        emailEx = view.findViewById(R.id.emailException);
        emailEx.setVisibility(View.GONE);
        nextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                emailCheck = true;
                String emailStr = email.getText().toString();
                try {
                    constraints.usernameCheck(emailStr);
                    emailEx.setVisibility(View.GONE);
                } catch (IllegalArgumentException e) {
                    emailEx.setText(e.getMessage());
                    emailEx.setVisibility(View.VISIBLE);
                    emailCheck = false;
                }
                if (emailCheck) {
                    Sign_up_page sign_up_page = (Sign_up_page) getActivity();
                    sign_up_page.addToList(0, emailStr);
                    sign_up_page.replaceFragments(PasswordFrag2.class);
                }
                else
                    Toast.makeText(getActivity(), "Invalid email", Toast.LENGTH_SHORT).show();
            }
        });
        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Sign_up_page sign_up_page = (Sign_up_page) getActivity();
                sign_up_page.replaceFragments(Log_in_page.class);
            }
        });

    }
}