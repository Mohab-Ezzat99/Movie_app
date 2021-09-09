package com.example.movieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.movieapp.model.AllCategory;
import com.example.movieapp.network.MovieAPI;
import com.example.movieapp.network.RetrofitBuilder;
import com.example.movieapp.repository.Repository;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;

public class MovieViewModel extends AndroidViewModel {

    private Repository repository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository();
    }

    public Single<AllCategory> getAllCategories(){
        return repository.getAllCategories();
    }
}
