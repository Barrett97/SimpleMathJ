package com.example.simplemathj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;

public class ThirdFragment extends Fragment {

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
        EditText eq = view.findViewById(R.id.editTextNumber);
        eq.setText(generateNumbers() + " X " + generateNumbers());


//        view.findViewById(R.id.axb).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(SecondFragment.this)
//                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
//            }
//        });
    }

    private String generateNumbers() {
        Random random = new Random();
        return Integer.toString(random.nextInt(9));
    }

}