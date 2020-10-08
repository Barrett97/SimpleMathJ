package com.example.simplemathj;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.lang.reflect.Array;
import java.security.Key;
import java.util.List;
import java.util.Random;

public class ThirdFragment extends Fragment {

    Button nextButton;
    EditText eq, ans;
    int a, b, state;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        state = ((MainActivity)getActivity()).getState();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        RelativeLayout layout = view.findViewById(R.id.thirdFragLayout);
//        int equationPosition = getResources().getDisplayMetrics().heightPixels/3;
        eq = view.findViewById(R.id.editTextQuestion);
        ans = view.findViewById(R.id.editTextAnswer);
        nextButton = view.findViewById(R.id.nextQ);
        nextButton.setVisibility(View.INVISIBLE);

        // This triggers the keyboard to appear
        ans.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        nextQuestion();

        setListeners();

    }

    private void setListeners() {
        ans.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER) {
                    if (ans.getText().toString().trim().length() > 0) {
                        if (checkAnswer(a, b, ans.getText().toString())) {
                            eq.setBackgroundColor(Color.GREEN);
                        } else {
                            eq.setBackgroundColor(Color.RED);
                        }
                        nextButton.setVisibility(View.VISIBLE);
                    }
                    return true;
                }
                return false;
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion();
            }
        });
    }

    // Return an integer between 0 and 9
    private int generateNumber() {
        Random random = new Random();
        return random.nextInt(9);
    }

    // Check if a * b is equal to ans
    private boolean checkAnswer(int a, int b, String ans) {
        if (state == 1 && a+b == Integer.parseInt(ans)) { // addition
            return true;
        } else if (state == 2 && a*b == Integer.parseInt(ans)) { // multiplication
            return true;
        } else if (state == 3 && a/b == Integer.parseInt(ans)) { // division
            return true;
        }
        return false;
    }

    // Replace the question with another
    private void nextQuestion() {
        ans.getText().clear();
        eq.setBackgroundColor(Color.WHITE);
        nextButton.setVisibility(View.INVISIBLE);

        a = generateNumber();
        b = generateNumber();

        String arith = "";

        if (((MainActivity)getActivity()).getState() == 1) { // addition
            arith = " + ";
        } else if (((MainActivity)getActivity()).getState() == 2) { // multiplication
            arith = " X ";
        } else if (((MainActivity)getActivity()).getState() == 3) { // division
            arith = " / ";
        }

        eq.setText(a + arith + b);
    }
}