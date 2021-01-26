package com.example.simplemathj;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simplemathj.databinding.FragmentTopicBinding;
import com.example.simplemathj.quote.Quote;
import com.example.simplemathj.quote.QuoteController;
import com.example.simplemathj.quote.SetQuoteListener;
import com.example.simplemathj.util.RandomNumber;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;

public class TopicFragment extends Fragment {

    private static final String TAG = "[TopicFragment]";

    TextView quote;
    TextView author;
    View quoteCard;
    List<Quote> quoteList;

    QuoteController quoteController;
    SetQuoteListener setQuoteListener;

    NavHostFragment navHostFragment;
    NavController navController;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentTopicBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_topic, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = ((MainActivity)getActivity()).getNavController();

        init();
        setViews(view);
        setListeners(view);
        setQuote(quote);
    }

    private void init() {
        quoteController = new QuoteController();
    }

    private void setQuote(TextView quote) {

        setQuoteListener = response -> {
            quoteList = response.body();
            assert quoteList != null;
            int i = RandomNumber.generateBetween(quoteList.size()-1, 0);
            quote.setText(quoteList.get(i).getText());
            String authorText = "- " + quoteList.get(i).getAuthor();
            author.setText(authorText);
        };

        quoteController.start(setQuoteListener);
    }

    private void setViews(View view) {
        quoteCard = view.findViewById(R.id.quoteCard);
        quote = view.findViewById(R.id.textViewQuote);
        author = view.findViewById(R.id.textViewAuthor);
    }

    // TODO: currently not being called properly in fragment_topic.xml
    public void navToMathTopics() {
        Log.d(TAG, "navToMathTopics()");
        NavHostFragment.findNavController(TopicFragment.this)
                .navigate(R.id.action_TopicFragment_to_MathFragment);
    }

    private void setListeners(View view) {
        view.findViewById(R.id.mathButton).setOnClickListener(v -> {
            Log.i(TAG, "math button");
            navController.navigate(R.id.action_TopicFragment_to_MathFragment);
        });

        view.findViewById(R.id.langButton).setOnClickListener(v ->
                navController.navigate(R.id.action_TopicFragment_to_LanguageFragment));

        // retrieves random quote
        quoteCard.setOnClickListener(v -> {
            int i = RandomNumber.generateBetween(quoteList.size()-1, 0);
            quote.setText(quoteList.get(i).getText());
            try {
                String quote;
                if (quoteList.get(i).getAuthor().equals("null")) {
                    quote = "\"" + "unknown" + "\"";
                } else {
                    quote = "- " + quoteList.get(i).getAuthor();
                }
                author.setText(quote);
            } catch (NullPointerException e) {
                Log.d(TAG, "null quote");
            }
        });
    }
}