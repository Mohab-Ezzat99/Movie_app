package com.example.movieapp.model;

import java.util.ArrayList;

public class AllCategory {

    private ArrayList<MoviesCategory> categories;

    public AllCategory(ArrayList<MoviesCategory> categories) {
        this.categories = categories;
    }

    public ArrayList<MoviesCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<MoviesCategory> categories) {
        this.categories = categories;
    }
}
