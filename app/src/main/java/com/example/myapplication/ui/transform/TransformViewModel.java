package com.example.myapplication.ui.transform;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class TransformViewModel extends ViewModel {


    private final MutableLiveData<List<String>> mTexts;
    private final MutableLiveData<String> userIdd = new MutableLiveData<>();
    private final MutableLiveData<Boolean> userRole = new MutableLiveData<>();
    public TransformViewModel() {
        mTexts = new MutableLiveData<>();
        List<String> texts = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            texts.add(String.valueOf(i));
        }
        mTexts.setValue(texts);
    }
    public void setUserRole(boolean value) {
        userRole.setValue(value);
    }
    public LiveData<List<String>> getTexts() {
        return mTexts;
    }
    public LiveData<Boolean> getUserRole() {
        return userRole;
    }
    public LiveData<String> getUserIdd() {
        return userIdd;
    }

    public void setUserId(String id) {
        userIdd.setValue(id);
    }
}