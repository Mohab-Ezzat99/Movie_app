package com.example.movieapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryItem implements Serializable {
    private String title,subtitle,description,thumb;
    private ArrayList<String> sources;

    public CategoryItem(String title, String subtitle, String description, String thumb, ArrayList<String> sources) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.thumb = thumb;
        this.sources = sources;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public ArrayList<String> getSources() {
        return sources;
    }

    public void setSources(ArrayList<String> sources) {
        this.sources = sources;
    }
}
