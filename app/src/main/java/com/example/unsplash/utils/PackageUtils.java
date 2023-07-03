package com.example.unsplash.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.unsplash.UnsplashPhotoApp;

public class PackageUtils {
    public static int packageCode() {
        PackageManager manager = UnsplashPhotoApp.getContext().getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(UnsplashPhotoApp.getContext().getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static String packageName() {
        PackageManager manager = UnsplashPhotoApp.getContext().getPackageManager();
        String name = null;
        try {
            PackageInfo info = manager.getPackageInfo(UnsplashPhotoApp.getContext().getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }
}
