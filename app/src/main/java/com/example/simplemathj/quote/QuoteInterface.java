package com.example.simplemathj.quote;

import java.util.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface QuoteInterface {
    @GET("api/quotes/{page}")
    Observable<> getData(@Path("page") String page);
}
