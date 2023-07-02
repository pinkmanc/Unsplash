package com.example.usplash.ui.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


import com.example.upsplash.R;
import com.example.usplash.UnsplashConstant;
import com.example.usplash.ui.fragment.LittlePhotoFragment;
import com.example.usplash.utils.ResUtils;
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
        initView();

    }
    protected void initView(){
        toolbar = findViewById(R.id.toolbar);
        drawer_layout_nav = findViewById(R.id.drawer_layout_nav);
        tv_nav_title = drawer_layout_nav.getHeaderView(0).findViewById(R.id.tv_nav_title);
        drawer_layout = findViewById(R.id.drawer_layout);
        drawer_layout_content_main = findViewById(R.id.drawer_layout_content_main);
        setSupportActionBar(toolbar);
        drawer_layout_nav.setItemIconTintList(null);
        drawer_layout_nav.setNavigationItemSelectedListener(this);




    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_see_little_photo:
                if (mFgManager.findFragmentByTag(UnsplashConstant.FG_LITTLE_PHOTO) == null) {
                    mFgManager.beginTransaction().replace(R.id.drawer_layout_content_main,
                            LittlePhotoFragment.newInstance(), UnsplashConstant.FG_LITTLE_PHOTO).commit();
                    toolbar.setTitle(ResUtils.getString(R.string.menu_see_little_photo));
                }
                break;

        }

        return true;
    }
}
