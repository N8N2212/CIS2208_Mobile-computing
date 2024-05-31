package com.example.myfitness.ui.bmi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BmiViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BmiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Bmi fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}