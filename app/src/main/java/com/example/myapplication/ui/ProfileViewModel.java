package com.example.myapplication.ui;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
public class ProfileViewModel extends ViewModel {



    private final MutableLiveData<String> userName = new MutableLiveData<>();

    public LiveData<String> getUserIdd() {
        return userIdd;
    }

    private final MutableLiveData<String> userIdd = new MutableLiveData<>();
    private final MutableLiveData<String> userEmail = new MutableLiveData<>();
    private final MutableLiveData<Boolean> userRole = new MutableLiveData<>();



    public void setUserId(String id) {
        userIdd.setValue(id);
    }

    public void setUserName(String name) {
        userName.setValue(name);
    }

    public void setUserEmail(String email) {
        userEmail.setValue(email);
    }
    public void setUserRole(boolean value) {
        userRole.setValue(value);
    }
    public LiveData<String> getUserName() {
        return userName;
    }

    public LiveData<String> getUserEmail() {
        return userEmail;
    }
    public LiveData<Boolean> getUserRole() {
        return userRole;
    }
}