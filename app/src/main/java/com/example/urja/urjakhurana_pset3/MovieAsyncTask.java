package com.example.urja.urjakhurana_pset3;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.Toast;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class MovieAsyncTask extends AsyncTask<String, Integer, String> {

    ResultActivity activity;
    Context context;

    // constructor
    public MovieAsyncTask(ResultActivity activity) {
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    protected void onPreExecute() {
        Toast.makeText(context, "Searching for movie", Toast.LENGTH_SHORT).show();
    }

    protected String doInBackground(String... params) {
        // get results of search
        return HttpRequestHelper.downloadFromServer(params);
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        MovieData movieData = null;
        Bitmap bmp;
        // if nothing is found
        if (result.equals("{\"Response\":\"False\",\"Error\":\"Movie not found!\"}")) {
            Toast.makeText(context, "No data was found", Toast.LENGTH_SHORT).show();
            // close screen since no results
            this.activity.finish();
        } else {
            try {
                // get all the information of the movie from the json file
                JSONObject readObj = new JSONObject(result);
                String plot = readObj.getString("Plot");
                String title = readObj.getString("Title");
                String posterUrl = readObj.getString("Poster");
                String year = readObj.getString("Year");
                String director = readObj.getString("Director");
                String actors = readObj.getString("Actors");
                // get image from URL of image by using asynctask
                ImageFromURL image = new ImageFromURL(this);
                bmp = image.execute(posterUrl).get();
                // make movie object by connecting all data of the movie with each other
                movieData = new MovieData(title, plot, bmp, year, director, actors);
            } catch (JSONException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            this.activity.setData(movieData);
        }
    }
}
