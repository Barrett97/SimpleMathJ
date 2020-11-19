package com.example.simplemathj.math;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.simplemathj.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddSubBoardFragment extends Fragment {

    NumberPicker cell00;
    NumberPicker cell01;
    NumberPicker cell02;
    NumberPicker cell03;
    NumberPicker cell04;

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

    NumberPicker cell30;
    NumberPicker cell31;
    NumberPicker cell32;
    NumberPicker cell33;
    NumberPicker cell34;


    List[][] numberPickers = new ArrayList[][]{};

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
        populateNumberPickers();
    }

    private void init(View v) {
        cell00 = v.findViewById(R.id.npCell00);
        cell01 = v.findViewById(R.id.npCell01);
        cell02 = v.findViewById(R.id.npCell02);
        cell03 = v.findViewById(R.id.npCell03);
        cell04 = v.findViewById(R.id.npCell04);

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

        cell30 = v.findViewById(R.id.npCell30);
        cell31 = v.findViewById(R.id.npCell31);
        cell32 = v.findViewById(R.id.npCell32);
        cell33 = v.findViewById(R.id.npCell33);
        cell34 = v.findViewById(R.id.npCell34);
    }

    private void populateNumberPickers() {
        List<NumberPicker> nps = new ArrayList<>();
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
        for (NumberPicker i : nps) {
            i.setMinValue(0);
            i.setMaxValue(9);
            i.setWrapSelectorWheel(false);
            i.setValue(0);
        }
    }
}
