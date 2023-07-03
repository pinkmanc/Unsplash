package com.example.unsplash;

import android.app.Application;

/**
 *  描述: Application类
 *
 * @author pinkmanc on 2023/7/3 20:34
 */

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
