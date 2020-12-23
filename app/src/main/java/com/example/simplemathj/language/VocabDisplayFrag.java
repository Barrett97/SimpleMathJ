package com.example.simplemathj.language;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simplemathj.R;
import com.example.simplemathj.databinding.FragmentVocabDisplayBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class VocabDisplayFrag extends Fragment {

    private VocabViewModel vocabViewModel;
    private FragmentVocabDisplayBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vocabViewModel = new ViewModelProvider(requireActivity()).get(VocabViewModel.class);
        binding = FragmentVocabDisplayBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(vocabViewModel);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
