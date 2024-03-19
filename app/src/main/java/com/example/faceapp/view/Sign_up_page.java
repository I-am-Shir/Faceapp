package com.example.faceapp.view;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.faceapp.R;
import com.example.faceapp.data.repository.UsersRepository;
import com.example.faceapp.model.User;
import com.example.faceapp.model.UserLocalStore;
import com.example.faceapp.data.network.RetrofitClient;
import com.example.faceapp.data.network.ApiService;
import com.example.faceapp.model.UserRequestBody;
import com.example.faceapp.view.fragments.EmailFrag1;
import com.example.faceapp.viewmodel.LoginViewModel;
import com.example.faceapp.viewmodel.SignUpViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;


public class Sign_up_page extends AppCompatActivity {
    String[] signUpInfo = new String[5];
    private SignUpViewModel signUpViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        setContentView(R.layout.activity_sign_up_page);

        EmailFrag1 email_fragment1 = new EmailFrag1();

        replaceFragments(email_fragment1.getClass());
        }
    public void replaceFragments(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment)
                .commit();
    }

    public void storeUser(User user){
        UserRequestBody requestBody = new UserRequestBody(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getProfilePhoto());
        signUpViewModel.signUpUser(requestBody, new SignUpViewModel.SignUpCallback() {
            @Override
            public void onSignUpSuccess(User user) {
                // Handle successful sign-up
                Toast.makeText(Sign_up_page.this, "User signed up successfully", Toast.LENGTH_SHORT).show();
                // Optionally, navigate to another activity or perform other actions
            }

            @Override
            public void onSignUpFailure(String errorMessage) {
                // Handle sign-up failure
                Toast.makeText(Sign_up_page.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        Intent i = new Intent(Sign_up_page.this, Log_in_page.class);
        startActivity(i);
    }
    public void addToList(int placement, String valueToInsert) {
      signUpInfo[placement] = valueToInsert;
    }
    public User setUser() {
        return new User(signUpInfo[0], signUpInfo[1], signUpInfo[2], signUpInfo[3], Uri.parse(signUpInfo[4]));
    }
}