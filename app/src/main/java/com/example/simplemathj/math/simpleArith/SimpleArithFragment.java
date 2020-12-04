package com.example.simplemathj.math.simpleArith;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.InputMethodManager;

import com.example.simplemathj.R;
import com.example.simplemathj.databinding.FragmentSimpleArithBinding;
import com.example.simplemathj.util.MathChecker;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import io.*;

/*
* This fragment contains the simple arithmetic questions
*
*/
public class SimpleArithFragment extends Fragment {

    private SimpleArithViewModel simpleArithViewModel;
    private FragmentSimpleArithBinding binding;

    @Override
    public View onCreateView(
            @NotNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        simpleArithViewModel = new ViewModelProvider(requireActivity()).get(SimpleArithViewModel.class);
        binding = FragmentSimpleArithBinding.inflate(inflater);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        triggerKeyboard();
        nextQuestion();
        setListeners();


        if (savedInstanceState != null) {
            simpleArithViewModel.setFirstNumber(savedInstanceState.getInt("firstNumber"));
            simpleArithViewModel.setSecondNumber(savedInstanceState.getInt("secondNumber"));
            String question = simpleArithViewModel.getFirstNumber()
                    + simpleArithViewModel.getSign()
                    + simpleArithViewModel.getSecondNumber();
            binding.textViewQuestion.setText(question);
        }
    }

    /*
    Set the listeners for this class
     */
    private void setListeners() {

        binding.editTextAnswer.setOnKeyListener((view, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_ENTER) {
                if (binding.editTextAnswer.getText().toString().length() > 0) {
                    binding.rightWrongSymbol.setVisibility(View.VISIBLE);
                    if (checkAnswer(simpleArithViewModel.getFirstNumber(),
                            simpleArithViewModel.getSecondNumber(),
                            binding.editTextAnswer.getText().toString())) {
                        binding.rightWrongSymbol.setText(R.string.check);
                        binding.rightWrongSymbol.setTextColor(Color.GREEN);
                    } else {
                        binding.rightWrongSymbol.setText(R.string.cross);
                        binding.rightWrongSymbol.setTextColor(Color.RED);
                    }
                    binding.nextQ.setVisibility(View.VISIBLE);
                }
                return true;
            }
            return false;
        });

        // Load next question
        binding.nextQ.setOnClickListener(view -> nextQuestion());
    }

    /*
    Check if a * b is equal to ans
     */
    private boolean checkAnswer(int a, int b, String ans) {
        int answer = Integer.parseInt(ans);
        boolean isCorrect = false;
        switch (simpleArithViewModel.getState()) {
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

    /*
    Replace the question with the next
     */
    public void nextQuestion() {
        binding.editTextAnswer.getText().clear();
        binding.rightWrongSymbol.setVisibility(View.INVISIBLE);
        binding.nextQ.setVisibility(View.INVISIBLE);

        // TODO: make settable bounds
        simpleArithViewModel.setFirstNumberRand();
        simpleArithViewModel.setSecondNumberRand();
        String question = simpleArithViewModel.getFirstNumber() + simpleArithViewModel.getSign() + simpleArithViewModel.getSecondNumber();

        binding.textViewQuestion.setText(question);
    }

    /*
    Pull up the keyboard
     */
    private void triggerKeyboard() {
        binding.editTextAnswer.requestFocus();
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("firstNumber", simpleArithViewModel.getFirstNumber());
        outState.putInt("secondNumber", simpleArithViewModel.getSecondNumber());
    }
}