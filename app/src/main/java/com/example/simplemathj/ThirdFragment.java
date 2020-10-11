package com.example.simplemathj;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Random;

public class ThirdFragment extends Fragment {

    Boolean answered;
    Button nextButton;
    EditText eq, ans;
    TextView checkcross;
    int a, b, state;
    String arith;

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
        eq = view.findViewById(R.id.editTextQuestion);
        ans = view.findViewById(R.id.editTextAnswer);
        nextButton = view.findViewById(R.id.nextQ);
        nextButton.setVisibility(View.INVISIBLE);
        checkcross = view.findViewById(R.id.checkcross);
        state = ((MainActivity)getActivity()).getState();
        answered = false;
    }
    /*
    Set the listeners for this class
     */
    private void setListeners() {
        /*
        While the question isn't answered
         */
        ans.setOnKeyListener((view, i, keyEvent) -> {
            if (answered == false) {
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
            } else if (answered){
                nextQuestion();
                answered = false;
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
        Log.d("arith", arith);
    }

    /*
    Return a random integer
     */
    private int generateNumber() {
        Random random = new Random();
        return random.nextInt(9);
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
    Replace the question with another
     */
    private void nextQuestion() {

        checkcross.setVisibility(View.INVISIBLE);
        ans.getText().clear();
        nextButton.setVisibility(View.INVISIBLE);

        a = generateNumber();
        b = generateNumber();

        eq.setText(a + arith + b);
    }

    /*
    Pull up the keyboard
     */
    private void triggerKeyboard() {
        ans.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}