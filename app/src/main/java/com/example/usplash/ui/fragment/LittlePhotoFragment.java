package com.example.usplash.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.upsplash.R;
import com.google.android.material.tabs.TabLayout;
import io.reactivex.disposables.CompositeDisposable;

public class LittlePhotoFragment extends Fragment {
    private Context mContext;
    private TabLayout tl_little_unsplash;
    private ViewPager vp_content;
    protected CompositeDisposable mSubscriptions;

    public static LittlePhotoFragment newInstance(){
        LittlePhotoFragment fragment =new LittlePhotoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_little_unsplash,container,false);
        tl_little_unsplash=view.findViewById(R.id.tl_little_unsplash);
        vp_content=view.findViewById(R.id.vp_content);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext=getActivity();
        mSubscriptions=new CompositeDisposable();
        TabFragmentPagerAdapter adapter=new TabFragmentPagerAdapter(this.getChildFragmentManager());
        vp_content.setAdapter(adapter);
        tl_little_unsplash.setupWithViewPager(vp_content);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.clear();
    }

    private class TabFragmentPagerAdapter extends FragmentPagerAdapter {
        private final String[] mTitles = {"unsplash.com"};

        private TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return UnsplashPTFragment.newInstance();
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
