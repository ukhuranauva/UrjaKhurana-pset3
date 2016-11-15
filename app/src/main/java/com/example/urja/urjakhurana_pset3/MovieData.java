package com.example.urja.urjakhurana_pset3;


import java.io.Serializable;

public class MovieData implements Serializable {
    String title;
    String description;

    public MovieData(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
