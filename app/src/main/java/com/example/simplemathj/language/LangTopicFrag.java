package com.example.simplemathj.language;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simplemathj.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LangTopicFrag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        view.findViewById(R.id.aplusb).setOnClickListener(view1 -> {
//            NavHostFragment.findNavController(LanguageFragment.this)
//                    .navigate(R.id.action_TopicFragment_to_ThirdFragment);
//
//        });
//
//        view.findViewById(R.id.axb).setOnClickListener(view2 -> {
//            NavHostFragment.findNavController(LanguageFragment.this)
//                    .navigate(R.id.action_TopicFragment_to_ThirdFragment);
//
//        });

    }
}
