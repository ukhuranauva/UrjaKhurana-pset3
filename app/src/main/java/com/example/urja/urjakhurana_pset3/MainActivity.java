package com.example.urja.urjakhurana_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void searchMovie(View view ) {
        EditText movieName = (EditText) findViewById(R.id.movieName);
        String movie = movieName.getText().toString();
        // goes to result page to showcase result of found movie
        Intent resultPage = new Intent(this, ResultActivity.class);
        resultPage.putExtra("moviename", movie);
        // to check which button to display on result page (delete or add)
        resultPage.putExtra("whichClass", "main");
        startActivity(resultPage);
        // reset input of searchbar
        movieName.setText("");
    }

    public void goToWatchlist(View view) {
        EditText movieName = (EditText) findViewById(R.id.movieName);
        // goes to watchlist
        Intent resultPage = new Intent(this, WatchListActivity.class);
        startActivity(resultPage);
        // reset input of searchbar
        movieName.setText("");
    }
}
