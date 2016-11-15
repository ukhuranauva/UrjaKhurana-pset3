package com.example.urja.urjakhurana_pset3;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HttpRequestHelper {

    protected static synchronized String downloadFromServer(String... params) {
        String result = "";
        String url1 = "http://www.omdbapi.com/?t=";
        String url2 = "&y=&plot=short&r=json";
        String title = params[0];
        String completeUrl = url1 + title + url2;
        URL url = null;

        try {
            url = new URL(completeUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection connection;
        if (url != null) {
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                Integer responseCode = connection.getResponseCode(); // wel of niet juiste antwoord (200-299)
                if (200 <= responseCode && responseCode <= 299) {
                    BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = bf.readLine()) != null) {
                        result = result + line;
                    }
                } else {
                    BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    // Communicate correct error
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
