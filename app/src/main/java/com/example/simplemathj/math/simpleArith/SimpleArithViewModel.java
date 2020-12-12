package com.example.simplemathj.math.simpleArith;

import com.example.simplemathj.math.MathTopicsEnum;
import com.example.simplemathj.util.RandomNumber;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SimpleArithViewModel extends ViewModel {

    private final MutableLiveData<EquationViewState> _eq = new MutableLiveData<>();
    public LiveData<EquationViewState> eq = _eq;

//    private MutableLiveData<String> _equation = new MutableLiveData<>();
//    public LiveData<String> equation = _equation;
//
//    private MutableLiveData<Integer> _firstNumber = new MutableLiveData<>();
//    public LiveData<Integer> firstNumber = _firstNumber;
//
//    private MutableLiveData<Integer> _secondNumber = new MutableLiveData<>();
//    public LiveData<Integer> secondNumber = _secondNumber;

    private MathTopicsEnum _state;

    private String sign;

    public String getEquationState() {
        return eq.getValue().toString();
    }


    public void init() {
        _eq.setValue(new EquationViewState(
                RandomNumber.generateTo(20),
                RandomNumber.generateTo(20),
                sign
        ));

//        _firstNumber.setValue(RandomNumber.generateTo(20));
//        _secondNumber.setValue(RandomNumber.generateTo(20));
    }

    public void setState(MathTopicsEnum state) {
        _state = state;
        setSign(_state);
    }

//    public String getEquation() {
//        return getFirstNumber().toString() + getSign() + getSecondNumber().toString();
//    }
//
//    public void setEquation() {
//        _equation.setValue(getFirstNumber().toString() + getSign() + getSecondNumber().toString());
//    }

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

//    public String getSign() {
//        return sign;
//    }
//
    public MathTopicsEnum getState() {
        return _state;
    }
//
//    public void setFirstNumberRand() {
//        _firstNumber.setValue(RandomNumber.generateTo(20));
//    }
//
    public Integer getFirstNumber() {
        if (_eq.getValue() != null) {
            return _eq.getValue().firstNumber;
        } else {
            return 0;
        }
    }
//
//    public void setSecondNumberRand() {
//        _secondNumber.setValue(RandomNumber.generateTo(20));
//    }
//
    public Integer getSecondNumber() {
        if (_eq.getValue() != null) {
            return _eq.getValue().secondNumber;
        } else {
            return 0;
        }
    }
//
//    public void setFirstNumber(int x) {
//        _firstNumber.setValue(x);
//    }
//
//    public void setSecondNumber(int x) {
//        _secondNumber.setValue(x);
//    }

    // todo:
    public void nextQuestion() {
        if (_eq.getValue() != null) {
            _eq.getValue().firstNumber = RandomNumber.generateTo(20);
            _eq.getValue().secondNumber = RandomNumber.generateTo(20);
        }
        System.out.println(_eq.getValue().firstNumber);
//        _firstNumber.setValue(RandomNumber.generateTo(20));
//        _secondNumber.setValue(RandomNumber.generateTo(20));
//        setEquation();
    }




}
