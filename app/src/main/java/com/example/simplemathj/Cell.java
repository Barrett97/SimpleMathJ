package com.example.simplemathj;

import android.widget.EditText;
import android.widget.RelativeLayout;

public class Cell {

    int row, column;
    int val;
    EditText number;

    public Cell(int i, int j) {
        this.row = i;
        this.column = j;

    }
}
