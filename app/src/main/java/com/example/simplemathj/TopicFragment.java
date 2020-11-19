package com.example.simplemathj;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simplemathj.quote.APIClient;
import com.example.simplemathj.quote.APIQuoteInterface;
import com.example.simplemathj.quote.Controller;
import com.example.simplemathj.quote.Quote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Tag;

import static android.content.ContentValues.TAG;

public class TopicFragment extends Fragment {

    private static final String TAG = "QUOTE";

    TextView quote;
    TextView author;
    APIQuoteInterface apiQuoteInterface;
    List<Quote> quoteList;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topic, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setViews(view);
        setListeners(view);
        setQuote(quote);
    }

    private void setQuote(TextView quote) {
        apiQuoteInterface = APIClient.getClient().create(APIQuoteInterface.class);
        Call<List<Quote>> call = apiQuoteInterface.getData();

        call.enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                Log.d(TAG, "onResponse: Server response: " + response.toString());
                quoteList = response.body();

                quote.setText(quoteList.get(0).getText());
                String authorText = "- " + quoteList.get(0).getAuthor();
                author.setText(authorText);
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage());
            }
        });
    }

    private void setViews(View view) {
        quote = view.findViewById(R.id.textViewQuote);
        author = view.findViewById(R.id.textViewAuthor);
    }

    private void setListeners(View view) {
        view.findViewById(R.id.mathButton).setOnClickListener(view1 -> NavHostFragment.findNavController(TopicFragment.this)
                .navigate(R.id.action_TopicFragment_to_SecondFragment));

        view.findViewById(R.id.langButton).setOnClickListener(view12 -> NavHostFragment.findNavController(TopicFragment.this)
                .navigate(R.id.action_TopicFragment_to_SecondFragment));

        quote.setOnClickListener(v -> {
            int i = generateNumber(quoteList.size()-1, 0);
            quote.setText(quoteList.get(i).getText());
            try {
                if (quoteList.get(i).getAuthor().equals("null")) {
                    String quote = "\"" + "unknown" + "\"";
                    author.setText(quote);
                } else {
                    String quote = "- " + quoteList.get(i).getAuthor();
                    author.setText(quote);
                }
            } catch (NullPointerException e) {
                Log.d(TAG, "null quote");
            }
        });
    }

    private int generateNumber(int max, int min) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        getParentFragmentManager().putFragment(outstate, "TopicFragment", TopicFragment);
    }


}