package com.example.simplemathj.math.simpleArith;

import com.example.simplemathj.math.MathTopicsEnum;
import com.example.simplemathj.util.MathChecker;
import com.example.simplemathj.util.RandomNumber;

import java.util.Objects;

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
        return Objects.requireNonNull(eq.getValue()).toString();
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

    public void setState(MathTopicsEnum state) {
        _state = state;
        setSign(_state);
        _eq.setValue(null); // New question on operation change
    }

    public MathTopicsEnum getState() {
        return _state;
    }

    public void nextQuestion() {

        Objects.requireNonNull(_eq.getValue()).firstNumber = RandomNumber.generateTo(20);
        _eq.getValue().secondNumber = RandomNumber.generateTo(20);

        // forces livedata change to notify observable
        _eq.setValue(_eq.getValue());
    }

    public boolean checkAnswer(String ans) {
        int answer = Integer.parseInt(ans);
        boolean isCorrect = false;
        int a = Objects.requireNonNull(_eq.getValue()).firstNumber;
        int b = _eq.getValue().secondNumber;
        switch (getState()) {
            case ADDITION:
                isCorrect = MathChecker.add(a, b, answer);
                break;
            case MULTIPLICATION:
                isCorrect = MathChecker.mult(a, b, answer);
                break;
            case SUBTRACTION:
                isCorrect = MathChecker.sub(a, b, answer);
                break;
            case DIVISION:
                isCorrect = MathChecker.div(a, b , answer);
                break;
        }
        return isCorrect;
    }
}
