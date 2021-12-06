package com.ducnb.navi.ui.number_transfer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NumberTransferViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NumberTransferViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}