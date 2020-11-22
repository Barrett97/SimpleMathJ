package com.example.simplemathj;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simplemathj.quote.Quote;
import com.example.simplemathj.quote.QuoteController;
import com.example.simplemathj.quote.SetQuoteListener;
import com.example.simplemathj.util.RandomNumber;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class TopicFragment extends Fragment {

    private static final String TAG = "QUOTE";

    TextView quote;
    TextView author;
    List<Quote> quoteList;
    QuoteController quoteController;
    SetQuoteListener setQuoteListener;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_topic, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
            int i = RandomNumber.generateBetween(quoteList.size()-1, 0);
            quote.setText(quoteList.get(i).getText());
            String authorText = "- " + quoteList.get(i).getAuthor();
            author.setText(authorText);
        };

        quoteController.start(setQuoteListener);
    }

    private void setViews(View view) {
        quote = view.findViewById(R.id.textViewQuote);
        author = view.findViewById(R.id.textViewAuthor);
    }

    private void setListeners(View view) {
        view.findViewById(R.id.mathButton).setOnClickListener(view1 ->
                NavHostFragment.findNavController(TopicFragment.this)
                               .navigate(R.id.action_TopicFragment_to_SecondFragment));

        view.findViewById(R.id.langButton).setOnClickListener(view12 ->
                NavHostFragment.findNavController(TopicFragment.this)
                               .navigate(R.id.action_TopicFragment_to_SecondFragment));

        // retrieves random quote
        quote.setOnClickListener(v -> {
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

    // TODO: retrieve last or random quote
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        getParentFragmentManager().putFragment(outstate, "TopicFragment", TopicFragment);
    }

}