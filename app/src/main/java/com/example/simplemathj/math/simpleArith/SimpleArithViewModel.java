package com.example.simplemathj.math.simpleArith;

import com.example.simplemathj.math.MathTopic;
import com.example.simplemathj.util.RandomNumber;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SimpleArithViewModel extends ViewModel {

    private MutableLiveData<String> arithmetic;

    private MutableLiveData<Integer> firstNumber;

    private MutableLiveData<Integer> secondNumber;

    private MutableLiveData<MathTopic> state;

    public void setState(MathTopic state) {
        this.state.setValue(state);
    }

    public LiveData<MathTopic> getState() {
        return state;
    }

    public void setFirstNumber() {
        firstNumber.setValue(RandomNumber.generateTo(20));
    }

    public LiveData<Integer> getFirstNumber() {
        return firstNumber;
    }

    public void setSecondNumber() {
        secondNumber.setValue(RandomNumber.generateTo(20));
    }

    public LiveData<Integer> getSecondNumber() {
        return secondNumber;
    }



}
