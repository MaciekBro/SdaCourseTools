package com.example.rent.sdacoursetools.ToReadApp;

import android.support.annotation.DrawableRes;

/**
 * Created by RENT on 2017-03-02.
 */
public class Book {


    @DrawableRes
    private int imageResourceId;
    private String title;
    private int id;
    private boolean isRead; //domyslnie false

    public Book(int id, @DrawableRes int imageResourceId, String title) {  //adnotacja jest po to by android studio mnie ostrzaegalo jesli cos zle wpisze
        this.id=id;
        this.imageResourceId = imageResourceId;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @DrawableRes
    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getId() {
        return id;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
