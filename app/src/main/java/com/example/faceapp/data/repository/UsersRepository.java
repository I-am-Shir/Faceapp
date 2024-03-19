package com.example.faceapp.data.repository;

import com.example.faceapp.data.network.ApiService;
import com.example.faceapp.data.network.RetrofitClient;
import com.example.faceapp.model.FriendRequestRequestBody;
import com.example.faceapp.model.FriendshipRequestBody;
import com.example.faceapp.model.User;
import com.example.faceapp.model.UserRequestBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {
    private ApiService.Users usersService;

    public UsersRepository() {
        usersService = RetrofitClient.getInstance().create(ApiService.Users.class);
    }

    public void addUser(UserRequestBody requestBody, Callback<User> callback) {
        Call<User> call = usersService.addUser(requestBody);
        call.enqueue(callback);
    }

    public void getUserByEmail(String token, String email, Callback<User> callback) {
        Call<User> call = usersService.getUserByEmail(token, email);
        call.enqueue(callback);
    }

    public void updateUser(String token, String email, UserRequestBody requestBody, Callback<User> callback) {
        Call<User> call = usersService.updateUser(token, email, requestBody);
        call.enqueue(callback);
    }

    public void deleteUser(String token, String email, Callback<User> callback) {
        Call<User> call = usersService.deleteUser(token, email);
        call.enqueue(callback);
    }

    public void getFriendList(String token, String email, Callback<List<User>> callback) {
        Call<List<User>> call = usersService.getFriendList(token, email);
        call.enqueue(callback);
    }

    public void sendFriendRequest(String token, String receiverEmail, Callback<String> callback) {
        Call<String> call = usersService.sendFriendRequest(token, receiverEmail);
        call.enqueue(callback);
    }

    public void rejectFriendRequest(String token, FriendRequestRequestBody requestBody, Callback<String> callback) {
        Call<String> call = usersService.rejectFriendRequest(token, requestBody);
        call.enqueue(callback);
    }

    public void acceptFriendRequest(String token, FriendRequestRequestBody requestBody, Callback<String> callback) {
        Call<String> call = usersService.acceptFriendRequest(token, requestBody);
        call.enqueue(callback);
    }

    public void deleteFriend(String token, FriendshipRequestBody requestBody, Callback<String> callback) {
        Call<String> call = usersService.deleteFriend(token, requestBody);
        call.enqueue(callback);
    }
}
