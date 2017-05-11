package com.ss.app.superbiz.intent;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ss.app.superbiz.NavigationDrawer;
import com.ss.app.superbiz.R;

public class DummyActivity extends AppCompatActivity {
    private static final String TAG = DummyActivity.class.getSimpleName();
    private Toolbar toolbar ;
    private DrawerLayout drawerLayout;
    private NavigationView mNavigationView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        toolbar       =  (Toolbar)findViewById(R.id.toolbar);
        drawerLayout  =  (DrawerLayout)findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationView();
    }

    private void setNavigationView() {
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationDrawer navigationDrawer = new NavigationDrawer(toolbar,drawerLayout,mNavigationView,this);
        navigationDrawer.setNavigationDrawer();

    }
}
