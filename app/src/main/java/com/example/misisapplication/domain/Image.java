package com.example.misisapplication.domain;

import android.graphics.Bitmap;

public class Image {

    private int id;
    private String path;

    public Image(int id, String path) {
        this.id = id;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", path=" + path +
                '}';
    }
}
