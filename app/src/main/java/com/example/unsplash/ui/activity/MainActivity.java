package com.example.unsplash.ui.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.upsplash.R;
import com.example.unsplash.UnsplashConstant;
import com.example.unsplash.ui.fragment.LittleUnsplashFragment;
import com.example.unsplash.utils.PackageUtils;
import com.example.unsplash.utils.ResUtils;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private NavigationView drawer_layout_nav;
    private TextView tv_nav_title;
    private ConstraintLayout drawer_layout_content_main;

    private FragmentManager mFgManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFgManager = getSupportFragmentManager();
        initView();
        initData();

    }
    protected void initView(){
        toolbar = findViewById(R.id.toolbar);
        drawer_layout_nav = findViewById(R.id.drawer_layout_nav);
        tv_nav_title = drawer_layout_nav.getHeaderView(0).findViewById(R.id.tv_nav_title);
        drawer_layout = findViewById(R.id.drawer_layout);
        drawer_layout_content_main = findViewById(R.id.drawer_layout_content_main);
        drawer_layout_nav.setItemIconTintList(null);
        drawer_layout_nav.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();


    }
    private void initData() {
        mFgManager.beginTransaction().replace(R.id.drawer_layout_content_main,
                LittleUnsplashFragment.newInstance(), UnsplashConstant.FG_LITTLE_PHOTO).commit();
        toolbar.setTitle(ResUtils.getString(R.string.menu_see_little_photo));
        String version = PackageUtils.packageName();
        if(version != null) {
            String msg = String.format(ResUtils.getString(R.string.menu_unsplash_photo_version), version);
            tv_nav_title.setText(msg);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_see_little_photo) {
            if (mFgManager.findFragmentByTag(UnsplashConstant.FG_LITTLE_PHOTO) == null) {
                mFgManager.beginTransaction().replace(R.id.drawer_layout_content_main,LittleUnsplashFragment.newInstance(), UnsplashConstant.FG_LITTLE_PHOTO).commit();
                toolbar.setTitle("图片");

            }
        } else if (id == R.id.nav_see_news) {
            // handle nav_see_news
        } else if (id == R.id.nav_use_check_weather) {
            // handle nav_use_check_weather
        } else if (id == R.id.nav_use_check_subway) {
            // handle nav_use_check_subway
        } else if (id == R.id.nav_use_tools) {
            // handle nav_use_tools
        } else if (id == R.id.nav_else_setting) {
            // handle nav_else_setting
        } else if (id == R.id.nav_else_about) {
            // handle nav_else_about
        }
        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }
}
