package com.example.usplash;

import android.app.Application;

import com.example.usplash.data.dto.UnsplashPhoto;

public class UnsplashPhotoApp extends Application {

    private static UnsplashPhotoApp context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        UnsplashInit.initTimber();
        UnsplashInit.initOKHttp(this);
    }

    public static UnsplashPhotoApp getContext() {
        return context;
    }
}
