package com.example.faceapp.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.faceapp.model.User;
import com.google.gson.Gson;

public class UserSessionManager {

    private static final String PREF_NAME = "UserSession";
    private static final String KEY_USER = "user";
    private static final String KEY_TOKEN = "token";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public UserSessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveUser(User user, String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Serialize user object to JSON
        String userJson = gson.toJson(user);

        // Store user JSON and token in SharedPreferences
        editor.putString(KEY_USER, userJson);
        editor.putString(KEY_TOKEN, token);

        // Commit changes
        editor.apply();
    }

    public User getUser() {
        String userJson = sharedPreferences.getString(KEY_USER, null);
        if (userJson != null) {
            // Deserialize user JSON to object
            return gson.fromJson(userJson, User.class);
        }
        return null;
    }

    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }

    public void clearSession() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USER);
        editor.remove(KEY_TOKEN);
        editor.apply();
    }
}