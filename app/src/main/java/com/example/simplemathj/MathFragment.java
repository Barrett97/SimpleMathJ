package com.example.simplemathj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class MathFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_math, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.aplusb).setOnClickListener(view1 -> {
            NavHostFragment.findNavController(MathFragment.this)
                    .navigate(R.id.action_SecondFragment_to_ThirdFragment);

            ((MainActivity)getActivity()).setState(1);
        });

        view.findViewById(R.id.axb).setOnClickListener(view2 -> {
            NavHostFragment.findNavController(MathFragment.this)
                    .navigate(R.id.action_SecondFragment_to_ThirdFragment);

            ((MainActivity)getActivity()).setState(2);
        });

        view.findViewById(R.id.adivb).setOnClickListener(view3 -> {
            NavHostFragment.findNavController(MathFragment.this)
                    .navigate(R.id.action_SecondFragment_to_ThirdFragment);

            ((MainActivity)getActivity()).setState(3);
        });

        view.findViewById(R.id.addBoard).setOnClickListener(view4 -> {
            NavHostFragment.findNavController(MathFragment.this)
                    .navigate(R.id.action_SecondFragment_to_AddBoardFragment);
        });

    }
}