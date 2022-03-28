package com.elibrown.mvvm;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.ArrayList;

public class ViewModel extends BaseObservable {

    private ArrayList<Photo> photoList;
    private final Photo placeHolder;
    private int currentIndexOfImage = 0;
    private boolean progressSpinner = true;

    public ViewModel(Context context) {

        placeHolder = new Photo();
        placeHolder.setTitle("Photo Title");
        placeHolder.setDescription("Photo Description");

        DataDownloader.getInstance().getImages(context,photos -> {
            photoList = photos;
            progressSpinner = false;
            notifyPropertyChanged(BR._all);
        });
    }

    @Bindable
    public Photo getCurrentPhoto(){
        if (photoList == null)
            return placeHolder;

        return photoList.get(currentIndexOfImage);
    }

    //@Bindable
    public int progressSpinnerVisibility(){
        return progressSpinner ? View.VISIBLE : View.GONE;
    }

    public void prevPic(){
        if (photoList == null)
            return;

        currentIndexOfImage = currentIndexOfImage == 0 ? photoList.size() -1 : --currentIndexOfImage;
        notifyPropertyChanged(BR._all);
    }

    public void nextPic(){
        if (photoList == null)
            return;

        currentIndexOfImage = currentIndexOfImage == photoList.size() -1 ? 0 : ++currentIndexOfImage;
        notifyPropertyChanged(BR._all);
    }
}
