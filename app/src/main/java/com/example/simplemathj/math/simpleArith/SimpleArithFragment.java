package com.example.simplemathj.math.simpleArith;

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
import com.example.simplemathj.math.MathTopic;
import com.example.simplemathj.util.MathCheck;
import com.example.simplemathj.util.RandomNumber;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

/*
* This fragment contains the simple arithmetic questions
*
*/
public class SimpleArithFragment extends Fragment {

    private SimpleArithViewModel simpleArithViewModel;

    private Button nextButton;
    private EditText ans;
    private TextView eq, rightWrongSymbol;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        simpleArithViewModel = new ViewModelProvider(requireActivity()).get(SimpleArithViewModel.class);

        System.out.println(simpleArithViewModel.getState().toString());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        triggerKeyboard();
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
        rightWrongSymbol = view.findViewById(R.id.rightWrongSymbol);
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
                    rightWrongSymbol.setVisibility(View.VISIBLE);
                    if (checkAnswer(simpleArithViewModel.getFirstNumber(),
                            simpleArithViewModel.getSecondNumber(),
                            ans.getText().toString())) {
                        rightWrongSymbol.setText(R.string.check);
                        rightWrongSymbol.setTextColor(Color.GREEN);
                    } else {
                        rightWrongSymbol.setText(R.string.cross);
                        rightWrongSymbol.setTextColor(Color.RED);
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
    Check if a * b is equal to ans
     */
    private boolean checkAnswer(int a, int b, String ans) {
        int answer = Integer.parseInt(ans);
        boolean isCorrect = false;
        switch (simpleArithViewModel.getState()) {
            case ADDITION:
                isCorrect = MathCheck.add(a, b, answer);
                break;
            case MULTIPLICATION:
                isCorrect = MathCheck.mult(a, b, answer);
                break;
            case SUBTRACTION:
                isCorrect = MathCheck.sub(a, b, answer);
                break;
            case DIVISION:
                isCorrect = MathCheck.div(a, b , answer);
                break;
        }
        return isCorrect;
    }

    /*
    Replace the question with the next
     */
    private void nextQuestion() {
        ans.getText().clear();
        rightWrongSymbol.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);

        // TODO: make settable bounds
        simpleArithViewModel.setFirstNumber();
        simpleArithViewModel.setSecondNumber();
        String question = simpleArithViewModel.getFirstNumber() + simpleArithViewModel.getSign() + simpleArithViewModel.getSecondNumber();

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