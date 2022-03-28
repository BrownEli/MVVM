package com.elibrown.mvvm;

import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;

public class DataDownloader {

    private static DataDownloader mInstance;

    public synchronized static DataDownloader getInstance(){

        if (mInstance == null)
            mInstance = new DataDownloader();

        return mInstance;
    }

    public void getImages(Context context,ImageDownloadCallback callback){

        ArrayList<Photo> photos = new ArrayList<>();
        String[] titles = context.getResources().getStringArray(R.array.titles);
        String[] descriptions = context.getResources().getStringArray(R.array.descriptions);
        String[] urls = context.getResources().getStringArray(R.array.urls);

        for (int i = 0;i < urls.length; i++){
            Photo photo = new Photo();
            photo.setUrl(urls[i]);
            photo.setTitle(titles[i]);
            photo.setDescription(descriptions[i]);
            photos.add(photo);
        }

        Handler handler = new Handler();
        handler.postDelayed(() -> callback.onCompletion(photos),3000);
    }


    public interface ImageDownloadCallback {
        void onCompletion(ArrayList<Photo> photos);
    }
}
