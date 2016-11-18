package com.example.urja.urjakhurana_pset3;


import android.graphics.Bitmap;

public class MovieData {
    String title;
    String description;
    Bitmap poster;
    String year;
    String director;
    String actors;

    // constructor of object
    public MovieData(String title, String description, Bitmap poster, String year, String director, String actors) {
        this.title = title;
        this.description = description;
        this.poster = poster;
        this.year = year;
        this.director = director;
        this.actors = actors;
    }
}
