package com.ss.app.superbiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.ss.app.superbiz.intent.LoginActivity;
import com.ss.app.superbiz.model.ChallengeApp;
import com.ss.app.superbiz.model.UserDetails;

import java.util.List;

public class SessionManager {
    public static final String ID = "id";
    public static final String MOBILE_NUMBER = "mobileNumber";
    public static final String USER_ID = "userId";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String DOB = "dob";
    public static final String AGREED_TC = "agreedTC";
    public static final String SPONSOR_ID= "sponsorId";
    public static final String AUTHTOKEN = "authtoken";
    public static final String DEVICE_REG_ID = "deviceRegId";
    public static final String ROLE = "role";
    public static final String IS_ACTIVE = "isActive";

    private static final String PREF_NAME = "SuperBizPref";
    private static final String IS_LOGIN = "IsLoggedIn";

    private static boolean challengeComplete ;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;
    private int PRIVATE_MODE = 0;

    private List<ChallengeApp> appList ;

    public SessionManager(Context context) {
        this.context = context;
        this.pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.editor = pref.edit();
    }

    public void createLoginSession(String mobileNumber,String password) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(MOBILE_NUMBER, mobileNumber);
        editor.putString(PASSWORD, password);
        editor.putBoolean(AGREED_TC,true);
        editor.commit();
    }
    public void createLoginSession(String id,String mobileNumber,String userId,String name,String email,String password,String dob,boolean agreedTC,String sponsorId,String authtoken,String deviceRegId,String role,boolean isActive ) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(ID, id);
        editor.putString(MOBILE_NUMBER, mobileNumber);
        editor.putString(USER_ID,userId);
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.putString(PASSWORD,password);
        editor.putString(DOB, dob);
        editor.putBoolean(AGREED_TC, agreedTC);
        editor.putString(SPONSOR_ID,sponsorId);
        editor.putString(AUTHTOKEN, authtoken);
        editor.putString(DEVICE_REG_ID, deviceRegId);
        editor.putString(ROLE,role);
        editor.putBoolean(IS_ACTIVE,isActive);
        editor.commit();
    }
    public void checkLogin() {
        if (!this.isLoggedIn()) {
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public UserDetails getUserDetails() {

        UserDetails user = new UserDetails();
        user.setId(pref.getString(ID, null));
        user.setMobileNumber(pref.getString(MOBILE_NUMBER, null));
        user.setUserId(pref.getString(USER_ID, null));
        user.setName(pref.getString(NAME, null));
        user.setEmail(pref.getString(EMAIL, null));
        user.setPassword( pref.getString(PASSWORD, null));
        user.setDob( pref.getString(DOB, null));
        user.setAgreedTC( pref.getBoolean(AGREED_TC, false));
        user.setSponsorId( pref.getString(SPONSOR_ID, null));
        user.setAuthtoken( pref.getString(AUTHTOKEN, null));
        user.setDeviceRegId( pref.getString(DEVICE_REG_ID, null));
        user.setRole( pref.getString(ROLE, null));
        user.setActive( pref.getBoolean(IS_ACTIVE, false));
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public boolean isChallengeComplete() {
         return challengeComplete;
    }

    public void setChallengeComplete(boolean challengeComplete) {
        this.challengeComplete = challengeComplete;
    }

    public List<ChallengeApp> getAppList() {
        return appList;
    }

    public void setAppList(List<ChallengeApp> appList) {
        this.appList = appList;
    }

}
