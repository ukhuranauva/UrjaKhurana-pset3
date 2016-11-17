package com.example.urja.urjakhurana_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class WatchListActivity extends AppCompatActivity {

    private ListView watchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);
        watchList = (ListView) findViewById(R.id.watchList);
        // get movies in watchlist
        SharedPreferences prefs = this.getSharedPreferences("settings",this.MODE_PRIVATE);
        String savedMovie = prefs.getString("movies", "");
        // add movies to arraylist to display in listview
        ArrayList<String> movies = new ArrayList<>();
        movies.add(savedMovie);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, movies);
        watchList.setAdapter(adapter);

        watchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // get page of movie by clicking on one of the movie names
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                TextView movieView = (TextView) view;
                String movie = movieView.getText().toString();
                Intent resultPage = new Intent(getApplicationContext(), ResultActivity.class);
                resultPage.putExtra("moviename", movie);
                // to display the right button since a movie on watchlist shouldn't be added again
                resultPage.putExtra("whichClass", "watch");
                startActivity(resultPage);
             }
        });
    }
}
