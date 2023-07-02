package com.example.usplash.ui.fragment;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import io.reactivex.disposables.CompositeDisposable;

public class LittleUnsplashFragment extends Fragment {
    private Context context;
    private TabLayout tl_little_unsplash;
    private ViewPager vp_content;
    protected CompositeDisposable compositeDisposable;
}
