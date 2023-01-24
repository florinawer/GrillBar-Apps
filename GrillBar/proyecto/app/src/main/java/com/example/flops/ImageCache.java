package com.example.flops;

import android.graphics.Bitmap;

public class ImageCache {

    private int id;
    private Bitmap img;

    public ImageCache(int id, Bitmap img) {
        this.id = id;
        this.img = img;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
