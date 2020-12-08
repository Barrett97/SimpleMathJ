package com.example.simplemathj.math.simpleArith;

import com.example.simplemathj.math.MathTopicsEnum;
import com.example.simplemathj.util.RandomNumber;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SimpleArithViewModel extends ViewModel {


    private MutableLiveData<Integer> _firstNumber = new MutableLiveData<>();
    public LiveData<Integer> firstNumber = _firstNumber;

    private MutableLiveData<Integer> _secondNumber = new MutableLiveData<>();
    public LiveData<Integer> secondNumber = _secondNumber;

    private MathTopicsEnum _state;

    private String sign;

    public void init() {
        _firstNumber.setValue(RandomNumber.generateTo(20));
        _secondNumber.setValue(RandomNumber.generateTo(20));
    }

    public void setState(MathTopicsEnum state) {
        _state = state;
        setSign(_state);
    }

    private void setSign(MathTopicsEnum state) {
        switch(state) {
            case ADDITION:
                sign = " + ";
                break;
            case SUBTRACTION:
                sign = " - ";
                break;
            case MULTIPLICATION:
                sign = " x ";
                break;
            case DIVISION:
                sign = " / ";
                break;
        }
    }

    public String getSign() {
        return sign;
    }

    public MathTopicsEnum getState() {
        return _state;
    }

    public void setFirstNumberRand() {
        _firstNumber.setValue(RandomNumber.generateTo(20));
    }

    public Integer getFirstNumber() {
        return firstNumber.getValue();
    }

    public void setSecondNumberRand() {
        _secondNumber.setValue(RandomNumber.generateTo(20));
    }

    public Integer getSecondNumber() {
        return secondNumber.getValue();
    }

    public void setFirstNumber(int x) {
        _firstNumber.setValue(x);
    }

    public void setSecondNumber(int x) {
        _secondNumber.setValue(x);
    }

    public void nextQuestion() {
        _firstNumber.setValue(RandomNumber.generateTo(20));
        _secondNumber.setValue(RandomNumber.generateTo(20));
    }




}
