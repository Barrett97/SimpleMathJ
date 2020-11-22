package com.example.simplemathj.quote;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIQuoteInterface {

    @GET("api/quotes")
    Call<List<Quote>> getData();

}
