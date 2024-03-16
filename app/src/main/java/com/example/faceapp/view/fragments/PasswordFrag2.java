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
import com.example.faceapp.view.NameFrag3;
import com.example.faceapp.view.Sign_up_page;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PasswordFrag2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PasswordFrag2 extends Fragment {

    private Constraints constraints;
    private Button nextBut, backBut;
    private EditText password, passwordVerify;
    private TextView passwordEx, passwordVerifyEx;
    private Boolean passwordCheck;

    public PasswordFrag2() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_password_frag2, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        constraints = new Constraints();
        nextBut = view.findViewById(R.id.nextBut);
        backBut = view.findViewById(R.id.backBut);
        password = view.findViewById(R.id.editTextPassword);
        passwordVerify = view.findViewById(R.id.PasswordVerify);
        passwordEx = view.findViewById(R.id.passwordException);
        passwordVerifyEx = view.findViewById(R.id.passwordVerifyEx);
        passwordEx.setVisibility(View.GONE);
        passwordVerifyEx.setVisibility(View.GONE);
        nextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                passwordCheck = true;
                String passwordStr = password.getText().toString();
                String passwordVerifyStr = passwordVerify.getText().toString();
                try {
                    constraints.passwordCheck(passwordStr);
                    passwordEx.setVisibility(View.GONE);
                } catch (Exception e) {
                    passwordEx.setText(e.getMessage());
                    passwordEx.setVisibility(View.VISIBLE);
                    passwordCheck = false;
                }
                try {
                    constraints.passwordMatchCheck(passwordStr, passwordVerifyStr);
                    passwordVerifyEx.setVisibility(View.GONE);
                } catch (Exception e) {
                    passwordVerifyEx.setText(e.getMessage());
                    passwordVerifyEx.setVisibility(View.VISIBLE);
                    passwordCheck = false;
                }
                if (passwordCheck) {
                    Sign_up_page sign_up_page = (Sign_up_page) getActivity();
                    sign_up_page.addToList(1, passwordStr);
                    sign_up_page.replaceFragments(NameFrag3.class);
                }
                else
                    Toast.makeText(getActivity(), "Invalid password", Toast.LENGTH_SHORT).show();
            }
        });
        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Sign_up_page sign_up_page = (Sign_up_page) getActivity();
                sign_up_page.replaceFragments(EmailFrag1.class);
            }
        });
    }
}