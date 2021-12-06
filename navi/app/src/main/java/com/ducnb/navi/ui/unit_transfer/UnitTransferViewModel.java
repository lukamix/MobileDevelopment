package com.ducnb.navi.ui.unit_transfer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UnitTransferViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UnitTransferViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}