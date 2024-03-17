package com.example.faceapp.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.faceapp.model.User;
import com.example.faceapp.model.UserRequestBody;
import com.example.faceapp.data.repository.UsersRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {

    private UsersRepository usersRepository;

    public SignUpViewModel() {
        usersRepository = new UsersRepository();
    }

    public void signUpUser(UserRequestBody requestBody, final SignUpCallback callback) {
        usersRepository.addUser(requestBody, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    callback.onSignUpSuccess(response.body());
                } else {
                    callback.onSignUpFailure("Failed to sign up user");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onSignUpFailure("Network error occurred");
            }
        });
    }

    public interface SignUpCallback {
        void onSignUpSuccess(User user);
        void onSignUpFailure(String errorMessage);
    }
}