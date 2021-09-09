package com.example.movieapp.network;

import com.example.movieapp.model.AllCategory;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieAPI {

    @GET("99fd994da714996b296f11c3c371d5ee/raw/28c4094ae48892efb71d5122c1fd72904088439b")
    Single<AllCategory> getAllCategories();
}
