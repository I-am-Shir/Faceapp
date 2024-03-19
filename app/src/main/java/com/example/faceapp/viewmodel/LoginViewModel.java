package com.example.faceapp.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.faceapp.data.repository.TokensRepository;
import com.example.faceapp.model.LoginResponse;
import com.example.faceapp.model.User;
import com.example.faceapp.model.UserLocalStore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private TokensRepository tokensRepository;
    private UserLocalStore userLocalStore;
    private MutableLiveData<Boolean> loginSuccess;

    public LoginViewModel() {
        tokensRepository = new TokensRepository();
        loginSuccess = new MutableLiveData<>();
    }

    public void init(Context context) {
        userLocalStore = new UserLocalStore(context);
    }

    // Method to perform login
    public void login(User user) {
        tokensRepository.login(user, new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    // Login successful
                    LoginResponse loginResponse = response.body();
                    String token = loginResponse.getToken();
                    User loggedInUser = loginResponse.getUser();

                    // Store user data and token
                    userLocalStore.storeUserData(loggedInUser, token);
                    userLocalStore.setUserLoggedIn(true);

                    loginSuccess.setValue(true);
                } else {
                    // Login failed
                    loginSuccess.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Network request failed
                loginSuccess.setValue(false);
            }
        });
    }

    // Method to observe login success or failure
    public LiveData<Boolean> observeLoginResult() {
        return loginSuccess;
    }
}