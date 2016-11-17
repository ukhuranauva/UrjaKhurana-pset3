package com.example.urja.urjakhurana_pset3;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

//http://stackoverflow.com/questions/5776851/load-image-from-url
public class ImageFromURL  extends AsyncTask<String, Bitmap, Bitmap> {

        MovieAsyncTask movie;
        public ImageFromURL(MovieAsyncTask movie) {
            this.movie = movie;
        }

        protected Bitmap doInBackground(String... params) {
            // get url of image
            String imageUrl = params[0];
            Bitmap image = null;
            try {
                URL imgUrl = new URL(imageUrl);
                InputStream in = imgUrl.openStream();
                // get bmp from url
                image = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return image;
        }

    }
