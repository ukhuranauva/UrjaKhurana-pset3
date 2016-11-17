package com.example.urja.urjakhurana_pset3;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle extras = getIntent().getExtras();
        // get which activity the data is from (search or watchlist)
        String activity = extras.getString("whichClass");
        String movieName = extras.getString("moviename");
        // get data of the movie to showcase
        getData(movieName);
        if (activity.equals("main")) {
            // display button to add movie to watchlist if movie was searched for
            Button addButton = (Button) findViewById(R.id.addButton);
            addButton.setVisibility(addButton.VISIBLE);
        } else if (activity.equals("watch")) {
            // display button to removie from watchlist if movie was tapped on from watchlist
            Button removeButton = (Button) findViewById(R.id.removeButton);
            removeButton.setVisibility(removeButton.VISIBLE);
        }
    }

    public void getData(String movieName) {
        // gets data of the movie
        MovieAsyncTask asyncTask = new MovieAsyncTask(this);
        asyncTask.execute(movieName);
    }

    public void setData(MovieData movieData) {
        // sets data of the movie
        TextView titleView = (TextView) findViewById(R.id.nameMovie);
        TextView directorView = (TextView) findViewById(R.id.directorMovie);
        ImageView posterView = (ImageView) findViewById(R.id.moviePoster);
        TextView yearView = (TextView) findViewById(R.id.yearMovie);
        TextView actorsView = (TextView) findViewById(R.id.actorsMovie);
        TextView descView = (TextView) findViewById(R.id.descMovie);
        // displays all the corresponding information per view
        titleView.setText(movieData.title);
        directorView.setText(movieData.director);
        posterView.setImageBitmap(movieData.poster);
        yearView.setText(movieData.year);
        actorsView.setText(movieData.actors);
        descView.setText(movieData.description);
    }

    public void addMovie(View view) {
        TextView titleView = (TextView) findViewById(R.id.nameMovie);
        String name = titleView.getText().toString();
        // save name of the movie to watchlist
        SharedPreferences prefs = this.getSharedPreferences("settings", this.MODE_APPEND);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("movies", name);
        editor.commit();
        Toast.makeText(this, "Saved movie", Toast.LENGTH_SHORT).show();
        // let button disappear since it is not useful anymore
        view.setVisibility(view.INVISIBLE);
    }

    public void removeMovie(View view) {
        SharedPreferences prefs = this.getSharedPreferences("settings", this.MODE_APPEND);
        SharedPreferences.Editor editor = prefs.edit();
        // delete movie from watchlist
        editor.remove("movies");
        editor.commit();
        Toast.makeText(this, "Seen", Toast.LENGTH_SHORT).show();
        // let button disappear since it is not useful anymore
        view.setVisibility(view.INVISIBLE);
    }
}
