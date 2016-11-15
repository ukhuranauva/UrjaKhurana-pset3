package com.example.urja.urjakhurana_pset3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void searchMovie(View view) {
        EditText movieName = (EditText) findViewById(R.id.movieName);
        String movie = movieName.getText().toString();
        MovieAsyncTask asyncTask = new MovieAsyncTask(this);
        asyncTask.execute(movie);
    }

    public void setData(MovieData movieData) {
        TextView results = (TextView) findViewById(R.id.resultsMovie);
        text = movieData.description;
        results.setText(text);
    }
}
