package com.example.movieapp.repository;

import com.example.movieapp.model.AllCategory;
import com.example.movieapp.network.MovieAPI;
import com.example.movieapp.network.RetrofitBuilder;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;

public class Repository {
    private final MovieAPI movieAPI= RetrofitBuilder.getInstance().create(MovieAPI.class);

    public Single<AllCategory> getAllCategories(){
        return movieAPI.getAllCategories();
    }
}
