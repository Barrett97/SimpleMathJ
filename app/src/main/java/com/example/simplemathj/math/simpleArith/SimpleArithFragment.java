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
* This fragment contains the view for the simple arithmetic operations:
* addition, subtraction, multiplication, division.
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
        binding.setLifecycleOwner(getViewLifecycleOwner());

        viewModel.init();

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setObserver();
        triggerKeyboard();
        setListeners();
    }

    private void setObserver() {
        viewModel.eq.observe(getViewLifecycleOwner(), integer -> {
            binding.editTextAnswer.getText().clear();
            binding.rightWrongSymbol.setVisibility(View.INVISIBLE);
            binding.nextQ.setVisibility(View.INVISIBLE);

            String question = viewModel.getEquationState();

            binding.textViewQuestion.setText(question);
        });
    }

    private void setListeners() {

        binding.editTextAnswer.setOnKeyListener((view, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_ENTER) {
                if (!binding.editTextAnswer.getText().toString().isEmpty()) {
                    binding.rightWrongSymbol.setVisibility(View.VISIBLE);
                    if (viewModel.checkAnswer(
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