package com.example.urja.urjakhurana_pset3;

import android.os.AsyncTask;
import android.widget.Toast;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;


public class MovieAsyncTask extends AsyncTask<String, Integer, String> {

    MainActivity activity;
    Context context;

    public MovieAsyncTask(MainActivity activity) {
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    protected void onPreExecute() {
        Toast.makeText(context, "Fetching data", Toast.LENGTH_LONG);
    }

    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer(params);
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        MovieData movieData = null;
        if (result.length() == 0) {
            Toast.makeText(context, "No data was found", Toast.LENGTH_LONG);
        } else {
            try {
                JSONObject readObj = new JSONObject(result);
                String plot = readObj.getString("Plot");
                String title = readObj.getString("Title");
                movieData = new MovieData(title, plot);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.activity.setData(movieData);
        }
    }
}
