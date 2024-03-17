package com.example.faceapp.data.repository;

import androidx.arch.core.executor.ArchTaskExecutor;

import com.example.faceapp.model.User;
import com.example.faceapp.data.network.ApiService;
import com.example.faceapp.data.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokensRepository {

    private ApiService.Tokens tokensService;

    public TokensRepository() {
        tokensService = RetrofitClient.getInstance().create(ApiService.Tokens.class);
    }

    public void login(User user, Callback<User> callback) {
        // Call the login API endpoint
        Call<User> call = tokensService.login(user);

        // Asynchronous network request
        call.enqueue(callback);
    }
}
