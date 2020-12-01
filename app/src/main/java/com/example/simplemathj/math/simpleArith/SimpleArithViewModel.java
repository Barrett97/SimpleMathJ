package com.example.simplemathj.math.simpleArith;

import com.example.simplemathj.math.MathTopic;
import com.example.simplemathj.util.RandomNumber;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SimpleArithViewModel extends ViewModel {


    private Integer _firstNumber;

    private Integer _secondNumber;

    private MathTopic _state;

    private String sign;

    public void setState(MathTopic state) {
        _state = state;
        setSign(_state);
    }

    private void setSign(MathTopic state) {
        switch(state) {
            case ADDITION:
                sign = " + ";
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

    public MathTopic getState() {
        return _state;
    }

    public void setFirstNumber() {
        _firstNumber = RandomNumber.generateTo(20);
    }

    public Integer getFirstNumber() {
        return _firstNumber;
    }

    public void setSecondNumber() {
        _secondNumber = RandomNumber.generateTo(20);
    }

    public Integer getSecondNumber() {
        return _secondNumber;
    }



}
