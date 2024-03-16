package com.example.faceapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.faceapp.view.fragments.PasswordFrag2;
import com.example.faceapp.view.fragments.PictureFrag4;


public class NameFrag3 extends Fragment {
    private Constraints constraints;
    private Button nextBut, backBut;
    private EditText firstName, lastName;
    private TextView firstNameEx, lastNameEx;
    private Boolean nameCheck;
    public NameFrag3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name_frag3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        constraints = new Constraints();
        nextBut = view.findViewById(R.id.nextBut);
        backBut = view.findViewById(R.id.backBut);
        firstName = view.findViewById(R.id.editFirstName);
        lastName = view.findViewById(R.id.editLastName);
        firstNameEx = view.findViewById(R.id.firstNameException);
        lastNameEx = view.findViewById(R.id.lastNameException);
        firstNameEx.setVisibility(View.GONE);
        lastNameEx.setVisibility(View.GONE);
        nextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                nameCheck = true;
                String firstNameStr = firstName.getText().toString();
                String lastNameStr = lastName.getText().toString();
                try {
                    constraints.firstNameCheck(firstNameStr);
                    firstNameEx.setVisibility(View.GONE);
                } catch (Exception e) {
                    firstNameEx.setText(e.getMessage());
                    firstNameEx.setVisibility(View.VISIBLE);
                    nameCheck = false;
                }
                try {
                    constraints.lastNameCheck(lastNameStr);
                    lastNameEx.setVisibility(View.GONE);
                } catch (Exception e) {
                    lastNameEx.setText(e.getMessage());
                    lastNameEx.setVisibility(View.VISIBLE);
                    nameCheck = false;
                }
                if (nameCheck) {
                    Sign_up_page sign_up_page = (Sign_up_page) getActivity();
                    sign_up_page.addToList(2, firstNameStr);
                    sign_up_page.addToList(3, lastNameStr);
                    sign_up_page.replaceFragments(PictureFrag4.class);
                }
                else
                    Toast.makeText(getActivity(), "Invalid name", Toast.LENGTH_SHORT).show();
            }
        });
        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Sign_up_page sign_up_page = (Sign_up_page) getActivity();
                sign_up_page.replaceFragments(PasswordFrag2.class);
            }
        });
    }
}