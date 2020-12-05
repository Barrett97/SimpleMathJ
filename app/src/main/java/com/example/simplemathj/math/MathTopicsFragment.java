package com.example.simplemathj.math;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simplemathj.R;
import com.example.simplemathj.math.simpleArith.SimpleArithViewModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

public class MathTopicsFragment extends Fragment {

    private SimpleArithViewModel simpleArithViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        simpleArithViewModel = new ViewModelProvider(requireActivity()).get(SimpleArithViewModel.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_math, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.aplusb).setOnClickListener(v -> {
            NavHostFragment.findNavController(MathTopicsFragment.this)
                    .navigate(R.id.action_TopicFragment_to_ThirdFragment);

            simpleArithViewModel.setState(MathTopicsEnum.ADDITION);
        });

        view.findViewById(R.id.asubb).setOnClickListener(v -> {
            NavHostFragment.findNavController(MathTopicsFragment.this)
                    .navigate(R.id.action_TopicFragment_to_ThirdFragment);

            simpleArithViewModel.setState(MathTopicsEnum.SUBTRACTION);
        });

        view.findViewById(R.id.axb).setOnClickListener(v -> {
            NavHostFragment.findNavController(MathTopicsFragment.this)
                    .navigate(R.id.action_TopicFragment_to_ThirdFragment);

            simpleArithViewModel.setState(MathTopicsEnum.MULTIPLICATION);
        });

        view.findViewById(R.id.adivb).setOnClickListener(v -> {
            NavHostFragment.findNavController(MathTopicsFragment.this)
                    .navigate(R.id.action_TopicFragment_to_ThirdFragment);

            simpleArithViewModel.setState(MathTopicsEnum.DIVISION);
        });

        view.findViewById(R.id.addBoard).setOnClickListener(v -> {
            NavHostFragment.findNavController(MathTopicsFragment.this)
                    .navigate(R.id.action_TopicFragment_to_AddBoardFragment);
        });

    }
}