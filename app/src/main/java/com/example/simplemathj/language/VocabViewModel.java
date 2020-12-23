package com.example.simplemathj.language;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VocabViewModel extends ViewModel {

    private MutableLiveData<String> _word = new MutableLiveData<>();
    public LiveData<String> word = _word;

}
