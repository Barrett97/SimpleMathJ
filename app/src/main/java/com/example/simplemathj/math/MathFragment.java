package com.example.simplemathj.math;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simplemathj.MainActivity;
import com.example.simplemathj.R;
import com.example.simplemathj.math.simpleArith.SimpleArithViewModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

public class MathFragment extends Fragment {

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

        view.findViewById(R.id.aplusb).setOnClickListener(view1 -> {
            NavHostFragment.findNavController(MathFragment.this)
                    .navigate(R.id.action_TopicFragment_to_ThirdFragment);

            simpleArithViewModel.setState(MathTopic.ADDITION);
            ((MainActivity) requireActivity()).setState(1);
        });

        view.findViewById(R.id.axb).setOnClickListener(view2 -> {
            NavHostFragment.findNavController(MathFragment.this)
                    .navigate(R.id.action_TopicFragment_to_ThirdFragment);

            simpleArithViewModel.setState(MathTopic.MULTIPLICATION);
            ((MainActivity) requireActivity()).setState(2);
        });

        view.findViewById(R.id.adivb).setOnClickListener(view3 -> {
            NavHostFragment.findNavController(MathFragment.this)
                    .navigate(R.id.action_TopicFragment_to_ThirdFragment);

            simpleArithViewModel.setState(MathTopic.DIVISION);
            ((MainActivity) requireActivity()).setState(3);
        });

        view.findViewById(R.id.addBoard).setOnClickListener(view4 -> {
            NavHostFragment.findNavController(MathFragment.this)
                    .navigate(R.id.action_TopicFragment_to_AddBoardFragment);
        });

    }
}