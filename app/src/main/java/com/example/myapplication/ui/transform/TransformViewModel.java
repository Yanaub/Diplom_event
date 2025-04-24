package com.example.myapplication.ui.transform;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.ui.clases.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TransformViewModel extends ViewModel {


    private final MutableLiveData<List<String>> mTexts;

    public TransformViewModel() {
        mTexts = new MutableLiveData<>();
        List<String> texts = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            texts.add(String.valueOf(i));
        }
        mTexts.setValue(texts);
    }

    public LiveData<List<String>> getTexts() {
        return mTexts;
    }

}