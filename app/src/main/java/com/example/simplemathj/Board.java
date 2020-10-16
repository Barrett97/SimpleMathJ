package com.example.simplemathj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Board extends Fragment {

    int a, b, i, j;
    Cell[] c;

    int gridRows, gridColumns;

    GridLayout gridLayout;
    Cell[] cell;


    public Board(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addition_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridLayout = view.findViewById(R.id.addBoardLayout);

        a = generateNumber(200, 2);
        b = generateNumber(200, 2);
        gridRows = (int)(Math.log10(b)+1);
        gridColumns = (int)(Math.log10(a)+1);
        // init

        makeBoard();
    }

    private void makeBoard() {
        for (int k = 0; k < gridRows; k++) {
            for (int j = 0; j < gridColumns; j++) {
                EditText e = new EditText();
                cell[k] = new Cell(k, j);
            }
        }
    }

    private int generateNumber(int max, int min) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
