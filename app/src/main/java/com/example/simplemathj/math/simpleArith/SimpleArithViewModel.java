package com.example.simplemathj.math.simpleArith;

import com.example.simplemathj.math.MathTopicsEnum;
import com.example.simplemathj.util.RandomNumber;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SimpleArithViewModel extends ViewModel {

    private final MutableLiveData<EquationViewState> _eq = new MutableLiveData<>();
    public LiveData<EquationViewState> eq = _eq;

    private MathTopicsEnum _state;

    private String sign;

    public void init() {
        if (_eq.getValue() == null) {
            _eq.setValue(new EquationViewState(
                    RandomNumber.generateTo(20),
                    RandomNumber.generateTo(20),
                    sign
            ));
        }
    }

    public String getEquationState() {
        return eq.getValue().toString();
    }

    public void setState(MathTopicsEnum state) {
        _state = state;
        setSign(_state);
        _eq.setValue(null); // New question on operation change
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

    public MathTopicsEnum getState() {
        return _state;
    }

    public Integer getFirstNumber() {
        if (_eq.getValue() != null) {
            return _eq.getValue().firstNumber;
        } else {
            return 0;
        }
    }

    public Integer getSecondNumber() {
        if (_eq.getValue() != null) {
            return _eq.getValue().secondNumber;
        } else {
            return 0;
        }
    }

    public void nextQuestion() {
        if (_eq.getValue() != null) {
            _eq.getValue().firstNumber = RandomNumber.generateTo(20);
            _eq.getValue().secondNumber = RandomNumber.generateTo(20);
        }
        _eq.setValue(_eq.getValue()); // forces change to notify observable
    }
}
