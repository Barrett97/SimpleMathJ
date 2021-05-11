package com.example.simplemathj.math.boardArith;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.simplemathj.R;
import com.example.simplemathj.util.RandomNumber;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.view.Gravity.CENTER;

// Class needs data binding and to be converted to .kt
public class AddSubBoardFragment extends Fragment {

    EditText cell00;
    EditText cell01;
    EditText cell02;
    EditText cell03;
    EditText cell04;

    TextView cell10;
    TextView cell11;
    TextView cell12;
    TextView cell13;
    TextView cell14;
    TextView cell20;
    TextView cell21;
    TextView cell22;
    TextView cell23;
    TextView cell24;

    EditText cell30;
    EditText cell31;
    EditText cell32;
    EditText cell33;
    EditText cell34;

    Button checkAns;

    List<TextView> textViewNumbers = new ArrayList<>();

    TextView[][] board = new TextView[][]{};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_addition_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        populateEditTexts();

        checkAns.setOnClickListener(v -> {
           if (checkAnswer()) {
               checkAns.setBackgroundColor(Color.GREEN);
           }

        });
    }

    private void init(View v) {

        checkAns = v.findViewById(R.id.checkAnswer);

        cell00 = v.findViewById(R.id.etCell00);
        cell01 = v.findViewById(R.id.etCell01);
        cell02 = v.findViewById(R.id.etCell02);
        cell03 = v.findViewById(R.id.etCell03);
        cell04 = v.findViewById(R.id.etCell04);

        cell30 = v.findViewById(R.id.etCell30);
        cell31 = v.findViewById(R.id.etCell31);
        cell32 = v.findViewById(R.id.etCell32);
        cell33 = v.findViewById(R.id.etCell33);
        cell34 = v.findViewById(R.id.etCell34);

        cell10 = v.findViewById(R.id.tvCell10);
        cell11 = v.findViewById(R.id.tvCell11);
        cell12 = v.findViewById(R.id.tvCell12);
        cell13 = v.findViewById(R.id.tvCell13);
        cell14 = v.findViewById(R.id.tvCell14);
        cell20 = v.findViewById(R.id.tvCell20);
        cell21 = v.findViewById(R.id.tvCell21);
        cell22 = v.findViewById(R.id.tvCell22);
        cell23 = v.findViewById(R.id.tvCell23);
        cell24 = v.findViewById(R.id.tvCell24);

        textViewNumbers = new ArrayList<>();

        textViewNumbers.add(cell11);
        textViewNumbers.add(cell12);
        textViewNumbers.add(cell13);
        textViewNumbers.add(cell14);

        textViewNumbers.add(cell21);
        textViewNumbers.add(cell22);
        textViewNumbers.add(cell23);
        textViewNumbers.add(cell24);

        cell10.setBackgroundColor(Color.GRAY);
        cell10.setTextSize(70);
        cell10.setInputType(InputType.TYPE_CLASS_NUMBER);
        cell10.setText("0");
        cell10.setGravity(CENTER);

        cell20.setBackgroundColor(Color.GRAY);
        cell20.setTextSize(70);
        cell20.setInputType(InputType.TYPE_CLASS_NUMBER);
        cell20.setText("0");
        cell20.setGravity(CENTER);
//        cell20.setText(0);

        for (TextView i : textViewNumbers) {
            i.setInputType(InputType.TYPE_CLASS_NUMBER);
            i.setTextSize(70);
            i.setGravity(CENTER);
            i.setText(String.valueOf(RandomNumber.generateTo(9)));
        }
    }

    private void populateEditTexts() {
        List<EditText> nps = new ArrayList<>();
        nps.add(cell00);
        nps.add(cell01);
        nps.add(cell02);
        nps.add(cell03);
        nps.add(cell04);
        nps.add(cell30);
        nps.add(cell31);
        nps.add(cell32);
        nps.add(cell33);
        nps.add(cell34);
        for (EditText i : nps) {
            i.setGravity(CENTER);
            i.setTextSize(70);
            i.setBackgroundColor(Color.GRAY);
        }
    }

    private boolean checkAnswer() {
        for (int i = 4; i >= 0; i--) {
            int colRes = 0;
            for (int j = 1; j >= 0; j--) {
                colRes += Integer.parseInt(board[i][j].getText().toString());
            }
            if (Integer.parseInt(cell34.getText().toString()) == getOnesDigit(colRes)) {
                return true;
            }
        }
        return false;
    }

    private int getOnesDigit(int x) {
        return x % 10;
    }
}
