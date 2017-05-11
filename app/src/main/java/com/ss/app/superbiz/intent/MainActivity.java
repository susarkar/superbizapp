package com.ss.app.superbiz.intent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ss.app.superbiz.R;
import com.ss.app.superbiz.SessionManager;
import com.ss.app.superbiz.model.ChallengeApp;
import com.ss.app.superbiz.rest.ChallengeService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static int SPLASH_TIME_OUT = 3000;
    private SessionManager sessionManager;
    private List<ChallengeApp> appList;
    private ChallengeService challengeService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appList = new ArrayList<ChallengeApp>();
        sessionManager = new SessionManager(getApplicationContext());
        challengeService = ChallengeService.getChallengeService(getApplicationContext());
        if(isAllAppInstalled()) sessionManager.setChallengeComplete(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();

/*               if(sessionManager.isLoggedIn()){
                    if (isAllAppInstalled()){
                        Intent di = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(di);
                        finish();
                    }else {
                        Intent ci = new Intent(MainActivity.this, ChallengeActivity.class);
                        startActivity(ci);
                        finish();
                    }
                }else {
                    Intent li = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(li);
                    finish();
                }*/
            }
        }, SPLASH_TIME_OUT);
    }


    private boolean isAllAppInstalled(){
        List<ChallengeApp> applist = challengeService.getAllChallenges();
        Log.d(TAG, "App Lsit " + appList);
        boolean retvalue = true;
        for(ChallengeApp c : appList){
            if (!isAppInstalled(c.getPackageName())){
                Log.d(TAG, "in loop " + retvalue);
                retvalue = false;
                break;
            }

        }
        Log.d(TAG, "return value " + retvalue);
        return retvalue;
    }
    private boolean isAppInstalled(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }
}
