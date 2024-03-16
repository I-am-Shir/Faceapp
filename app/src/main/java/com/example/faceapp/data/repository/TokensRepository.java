package com.example.faceapp.data.repository;

import com.example.faceapp.model.User;
import com.example.faceapp.data.network.ApiService;

import retrofit2.Call;

public class TokensRepository implements ApiService.Tokens {

    @Override
    public Call<User> login(User user) {
        return null;
    }
}
