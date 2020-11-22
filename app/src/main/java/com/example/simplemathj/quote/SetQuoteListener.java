package com.example.simplemathj.quote;

import java.util.List;

import retrofit2.Response;

public interface SetQuoteListener {

    void setQuoteList(Response<List<Quote>> response);

}
