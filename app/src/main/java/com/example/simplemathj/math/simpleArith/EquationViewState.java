package com.example.simplemathj.math.simpleArith;

public class EquationViewState {
    int firstNumber = 0;
    int secondNumber = 0;
    String arithmetic = "";

    EquationViewState(int firstNumber, int secondNumber, String arithmetic) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.arithmetic = arithmetic;
    }

    public String toString() {
        return firstNumber + arithmetic + secondNumber;
    }
}
