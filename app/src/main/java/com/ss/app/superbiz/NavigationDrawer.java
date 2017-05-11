package com.ss.app.superbiz;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.ss.app.superbiz.intent.DummyActivity;
import com.ss.app.superbiz.intent.HomeActivity;

/**
 * Created by Susanta on 23-04-2017.
 */

public class NavigationDrawer {
    private DrawerLayout  mDrawerLayout;
    private NavigationView mNavigationView;
    private Activity mActivity;
    private Toolbar mToolbar;

    public  NavigationDrawer(Toolbar mToolbar , DrawerLayout mDrawerLayout,NavigationView mNavigationView,Activity mActivity){
        this.mDrawerLayout = mDrawerLayout;
        this.mNavigationView = mNavigationView;
        this.mActivity = mActivity;
        this.mToolbar = mToolbar;
    }
    public  void setNavigationDrawer(){
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.home :
                        Intent homeIntent = new Intent(mActivity.getApplicationContext() , HomeActivity.class);
                        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        mDrawerLayout.closeDrawers();
                        mActivity.startActivity(homeIntent);
                        break;
                    case  R.id.invite :
                        Intent invite = new Intent(mActivity.getApplicationContext() , HomeActivity.class);
                        invite.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        mDrawerLayout.closeDrawers();
                        mActivity.startActivity(invite);
                        break;
                    case R.id.earnmore :
                        Intent earnMore = new Intent(mActivity.getApplicationContext() , DummyActivity.class);
                        earnMore.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        mActivity.startActivity(earnMore);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.incomejunction :
                        Intent iJunction = new Intent(mActivity.getApplicationContext() , DummyActivity.class);
                        iJunction.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        mActivity.startActivity(iJunction);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.shopandearn :
                        Intent shopEarn = new Intent(mActivity.getApplicationContext() , DummyActivity.class);
                        shopEarn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        mActivity.startActivity(shopEarn);
                        mDrawerLayout.closeDrawers();
                        break;


                    case  R.id.dashboard :
                        Intent dashborad = new Intent( mActivity.getApplicationContext() , HomeActivity.class);
                        dashborad.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        mActivity.startActivity(dashborad);
                        break;
                    case  R.id.redeem :
                        Intent redeem = new Intent( mActivity.getApplicationContext() , HomeActivity.class);
                        redeem.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        mActivity.startActivity(redeem);
                        break;

                    case R.id.setting :
                        Intent setting  = new Intent(mActivity.getApplicationContext() , DummyActivity.class);
                        setting.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        mActivity.startActivity(setting);
                        break;
                    case R.id.profile :
                        Intent profile  = new Intent(mActivity.getApplicationContext() , DummyActivity.class);
                        profile.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        mActivity.startActivity(profile);
                        break;
                }

                return false;
            }
        });

        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle( mActivity , mDrawerLayout, mToolbar , R.string.open , R.string.close ){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
    }
}
