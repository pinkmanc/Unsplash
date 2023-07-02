package com.example.usplash.utils;

import com.example.usplash.UnsplashPhotoApp;

public class ResUtils {
    public static String getString(int strId) {
        return UnsplashPhotoApp.getContext().getResources().getString(strId);
    }
}
