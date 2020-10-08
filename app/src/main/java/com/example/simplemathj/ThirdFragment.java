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
    int a, b;

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

//        RelativeLayout layout = view.findViewById(R.id.thirdFragLayout);
//        int equationPosition = getResources().getDisplayMetrics().heightPixels/3;
        eq = view.findViewById(R.id.editTextQuestion);
        ans = view.findViewById(R.id.editTextAnswer);
        nextButton = view.findViewById(R.id.nextQ);
        nextButton.setVisibility(View.INVISIBLE);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eq.setBackgroundColor(Color.WHITE);
                nextQuestion();
                nextButton.setVisibility(View.INVISIBLE);
                ans.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        });

        nextQuestion();

        ans.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        ans.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER) {
                    ans.getText().toString();
                    if (checkAnswer(a, b, ans.getText().toString())) {
                        eq.setText("Correct");
                        eq.setBackgroundColor(Color.GREEN);
                    } else {
                        eq.setText("Incorrect");
                        eq.setBackgroundColor(Color.RED);
                    }
                    nextButton.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

//        ans.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                if (i == EditorInfo.IME_ACTION_DONE) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        });

//        view.findViewById(R.id.axb).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(SecondFragment.this)
//                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
//            }
//        });
    }

    private int generateNumber() {
        Random random = new Random();
        return random.nextInt(9);
    }

    private boolean checkAnswer(int a, int b, String ans) {
        if (a*b == Integer.parseInt(ans)) {
            return true;
        }
        return false;
    }

    private void nextQuestion() {
        a = generateNumber();
        b = generateNumber();

        eq.setText(a + " X " + b);
    }
}