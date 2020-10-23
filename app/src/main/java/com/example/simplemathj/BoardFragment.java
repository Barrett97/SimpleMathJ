package com.example.simplemathj;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BoardFragment extends Fragment {

    int a, b, i, j;
    Cell[] c;

    int gridRows, gridColumns;

    GridLayout gridLayout;
    Cell[] cell;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addition_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridLayout = view.findViewById(R.id.LayoutBoard);
        a = generateNumber(200, 10);
        b = generateNumber(200, 10);
        gridRows = (int)(Math.log10(a));
        gridColumns = (int)(Math.log10(b));
        // init
        Log.d("gridrows", a + " " + gridRows);
        Log.d("gridcolumns", b + " " + gridColumns);
    }

    private void makeBoard() {
        EditText cell;
        for (int k = 0; k < gridRows; k++) {
            for (int j = 0; j < gridColumns; j++) {
                cell = new EditText(getContext());
                cell.setLayoutParams(new GridLayout.LayoutParams());
                gridLayout.addView(cell);
            }
        }
//        EditText et = new EditText(getContext());
//        et.setLayoutParams(new GridLayout.LayoutParams());
//        gridLayout.addView(et);
    }

    private int generateNumber(int max, int min) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
