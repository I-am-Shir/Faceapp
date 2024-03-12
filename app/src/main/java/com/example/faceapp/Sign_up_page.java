package com.example.faceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class Sign_up_page extends AppCompatActivity {
    String[] signUpInfo = new String[5];
    User user;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userLocalStore = new UserLocalStore(this);
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
        userLocalStore.storeUserData(user);
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