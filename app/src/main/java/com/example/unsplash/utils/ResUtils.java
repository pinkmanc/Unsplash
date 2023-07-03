package com.example.unsplash.utils;

import com.example.unsplash.UnsplashPhotoApp;

public class ResUtils {
    public static String getString(int strId) {
        return UnsplashPhotoApp.getContext().getResources().getString(strId);
    }
}
