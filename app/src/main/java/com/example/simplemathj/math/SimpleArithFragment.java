package com.example.simplemathj.math;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.simplemathj.MainActivity;
import com.example.simplemathj.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Objects;
import java.util.Random;

/*
* This fragment contains the simple arithmetic questions
*
*/
public class SimpleArithFragment extends Fragment {

    private static int a, b, state;
    private static String arith;
    private Button nextButton;
    private EditText ans;
    private TextView eq, checkcross;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        triggerKeyboard();
        setArith();
        nextQuestion();
        setListeners();

    }
    /*
    Initializes the views and sets variables
     */
    private void init(View view) {
        eq = view.findViewById(R.id.textViewQuestion);
        ans = view.findViewById(R.id.editTextAnswer);
        nextButton = view.findViewById(R.id.nextQ);
        nextButton.setVisibility(View.INVISIBLE);
        checkcross = view.findViewById(R.id.checkcross);
        state = ((MainActivity) requireActivity()).getState();
    }
    /*
    Set the listeners for this class
     */
    private void setListeners() {
        /*
        While the question isn't answered
         */
        ans.setOnKeyListener((view, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_ENTER) {
                if (ans.getText().toString().trim().length() > 0) {
                    checkcross.setVisibility(View.VISIBLE);
                    if (checkAnswer(a, b, ans.getText().toString())) {
                        checkcross.setText(R.string.check);
                        checkcross.setTextColor(Color.GREEN);
                    } else {
                        checkcross.setText(R.string.cross);
                        checkcross.setTextColor(Color.RED);
                    }
                    nextButton.setVisibility(View.VISIBLE);
                }
                return true;
            }
            return false;
        });
        // Load next question
        nextButton.setOnClickListener(view -> nextQuestion());
    }

    /*
    Set the arithmetic symbol for display
     */
    public void setArith() {
        if (state == 1) { // addition
            arith = " + ";
        } else if (state == 2) { // multiplication
            arith = " X ";
        } else if (state == 3) { // division
            arith = " / ";
        }
    }

    /*
    Return a random integer greater than x and less than y
     */
    private int generateNumber(int max, int min) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /*
    Check if a * b is equal to ans
     */
    private boolean checkAnswer(int a, int b, String ans) {
        int answer = Integer.parseInt(ans);
        if (state == 1 && a+b == answer) { // addition
            return true;
        } else if (state == 2 && a*b == answer) { // multiplication
            return true;
        } else if (state == 3 && a/b == answer) { // division
            return true;
        }
        return false;
    }

    /*
    Replace the question with the next
     */
    private void nextQuestion() {
        ans.getText().clear();
        checkcross.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);

        // TODO: make settable bounds
        a = generateNumber(20, 2);
        b = generateNumber(20, 2);
        String question = a + arith + b;

        eq.setText(question);
    }

    /*
    Pull up the keyboard
     */
    private void triggerKeyboard() {
        ans.requestFocus();
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}