package com.ss.app.superbiz.intent;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ss.app.superbiz.fragment.DashboardFragment;
import com.ss.app.superbiz.fragment.HomeFragment;
import com.ss.app.superbiz.NavigationDrawer;
import com.ss.app.superbiz.R;
import com.ss.app.superbiz.fragment.RedeemFragment;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private Toolbar toolbar ;
    private DrawerLayout drawerLayout;
    private NavigationView mNavigationView ;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar       =  (Toolbar)findViewById(R.id.toolbar);
        drawerLayout  =  (DrawerLayout)findViewById(R.id.drawer_layout);
        mTabLayout   =   (TabLayout) findViewById(R.id.tab_layout);
        mViewPager   =   (ViewPager) findViewById(R.id.view_pager);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationView();
        setTabLayout();
    }
    private void setNavigationView(){
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationDrawer navigationDrawer = new NavigationDrawer(toolbar,drawerLayout,mNavigationView,this);
        navigationDrawer.setNavigationDrawer();
    }
    private void setTabLayout(){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Fragment f1,f2,f3;
        f1 = new HomeFragment();
        f2 = new DashboardFragment();
        f3  = new RedeemFragment();
        adapter.addFragemnt( "Home" , f1 );
        adapter.addFragemnt( "Dashboard" , f2 );
        adapter.addFragemnt( "Redeem"    , f3 );
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    class  ViewPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> mListFrgament  =  new ArrayList<>();
        ArrayList<String>      mListString    =  new ArrayList<>();
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int i) {
            return mListFrgament.get(i);
        }
        @Override
        public int getCount(){
            return mListFrgament.size();
        }
        public  void addFragemnt(String name,Fragment mFragment){
            mListFrgament.add(mFragment);
            mListString.add(name);
        }
        @Override
        public CharSequence getPageTitle(int i) {
            return  mListString.get(i);
        }
    }
}
