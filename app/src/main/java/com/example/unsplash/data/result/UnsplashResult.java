package com.example.unsplash.data.result;

import com.example.unsplash.data.dto.UnsplashPhoto;

import java.util.ArrayList;

public class UnsplashResult {
    private Boolean error;
    private ArrayList<UnsplashPhoto> results;

    public UnsplashResult() { }

    public UnsplashResult(Boolean error, ArrayList<UnsplashPhoto> results) {
        this.error = error;
        this.results = results;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<UnsplashPhoto> getResults() {
        return results;
    }

    public void setResults(ArrayList<UnsplashPhoto> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "UnsplashResult{" +
                "error=" + error +
                ", results=" + results.toString() +
                '}';
    }
}
