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

/*
* This fragment contains the simple arithmetic questions
*
*/
public class SimpleArithFragment extends Fragment {

    private SimpleArithViewModel viewModel;
    private FragmentSimpleArithBinding binding;

    @Override
    public View onCreateView(
            @NotNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        viewModel = new ViewModelProvider(requireActivity()).get(SimpleArithViewModel.class);
        binding = FragmentSimpleArithBinding.inflate(inflater);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        if (viewModel.getSecondNumber() == null) {
            viewModel.init();
        }

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setObserver();
        triggerKeyboard();
        initQuestion();
        setListeners();
    }

    /*
    Observe for changes in the question
    */
    private void setObserver() {
        viewModel.secondNumber.observe(getViewLifecycleOwner(), integer -> {
            binding.editTextAnswer.getText().clear();
            binding.rightWrongSymbol.setVisibility(View.INVISIBLE);
            binding.nextQ.setVisibility(View.INVISIBLE);

            String question = viewModel.getFirstNumber() + viewModel.getSign() + viewModel.getSecondNumber();

            binding.textViewQuestion.setText(question);
        });
    }

    /*
    Set the listeners for this class
     */
    private void setListeners() {

        // Listen for "enter" key
        binding.editTextAnswer.setOnKeyListener((view, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_ENTER) {
                if (binding.editTextAnswer.getText().toString().length() > 0) {
                    binding.rightWrongSymbol.setVisibility(View.VISIBLE);
                    if (checkAnswer(viewModel.getFirstNumber(),
                            viewModel.getSecondNumber(),
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
    }

    /*
    Check if a * b is equal to ans given the arith state
     */
    private boolean checkAnswer(int a, int b, String ans) {
        int answer = Integer.parseInt(ans);
        boolean isCorrect = false;
        switch (viewModel.getState()) {
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
    Initialize the question from viewModel
     */
    public void initQuestion() {
        binding.editTextAnswer.getText().clear();
        binding.rightWrongSymbol.setVisibility(View.INVISIBLE);
        binding.nextQ.setVisibility(View.INVISIBLE);

        // TODO: make settable bounds
        String question = viewModel.getFirstNumber() + viewModel.getSign() + viewModel.getSecondNumber();

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


}