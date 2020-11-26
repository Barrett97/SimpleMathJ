package com.example.simplemathj.quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface APIQuoteInterface {

    @GET("api/quotes")
    Call<List<Quote>> getData();

}
