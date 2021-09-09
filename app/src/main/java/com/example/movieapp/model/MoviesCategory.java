package com.example.movieapp.model;

import java.util.ArrayList;
import java.util.List;

public class MoviesCategory {
    private String name;
    private ArrayList<CategoryItem> videos;

    public MoviesCategory(String name, ArrayList<CategoryItem> videos) {
        this.name = name;
        this.videos = videos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CategoryItem> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<CategoryItem> videos) {
        this.videos = videos;
    }
}
