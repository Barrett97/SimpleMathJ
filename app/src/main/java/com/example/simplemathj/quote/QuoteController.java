package com.example.simplemathj.quote;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class QuoteController implements Callback<List<Quote>>{

    private static final String BASE_URL = "https://type.fit/";

    SetQuoteListener setQuoteListener;

    public void start(SetQuoteListener setQuoteListener) {

        this.setQuoteListener = setQuoteListener;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIQuoteInterface APIQuoteInterface = retrofit.create(APIQuoteInterface.class);

        Call<List<Quote>> call = APIQuoteInterface.getData();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
        Log.d(TAG, "onResponse: Server response: " + response.toString());

        setQuoteListener.setQuoteList(response);

    }

    @Override
    public void onFailure(Call<List<Quote>> call, Throwable t) {
        Log.d(TAG, "Failure: Something went wrong: " + t.getMessage());
    }
}
