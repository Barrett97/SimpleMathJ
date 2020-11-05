package com.example.simplemathj.quote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quote {
    @SerializedName("text")
    @Expose
    String text;
    @SerializedName("author")
    @Expose
    String author;

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public Quote(String text, String author) {
        this.text = text;
        this.author = author;
    }

}