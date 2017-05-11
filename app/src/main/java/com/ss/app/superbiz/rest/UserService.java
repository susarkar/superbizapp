package com.ss.app.superbiz.rest;

import android.content.Context;
import android.util.Log;

import com.ss.app.superbiz.SessionManager;
import com.ss.app.superbiz.model.ChallengeApp;
import com.ss.app.superbiz.model.UserDetails;
import com.ss.app.superbiz.rest.api.ApiClient;
import com.ss.app.superbiz.rest.api.UserApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Susanta on 01-05-2017.
 */

public class UserService {
    private static UserService userService;
    private UserApi userApi;
    SessionManager sessionManager;
    private UserService(Context ctx, UserApi userApi){

    }
    private UserService(Context ctx){
        userApi = ApiClient.createApi(UserApi.class);
        sessionManager = new SessionManager(ctx);
    }
    public static UserService getUserService(Context ctx){
        if( userService == null ){
            userService = new UserService(ctx);
        }
        return userService;
    }
/*    public UserDetails login(String mobileNumber,String password){
        UserDetails u = null;
        Log.d(TAG,"mobileNmume = " + mobileNumber);
        Log.d(TAG,"password = " + password);
        Call<UserDetails> call = userApi
                .login(mobileNumber,password);
        call.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                UserDetails u = response.body();
                if (u != null) {
                    sessionManager.createLoginSession(
                            u.getId(),
                            u.getMobileNumber(),
                            u.getUserId(),
                            u.getName(),
                            u.getEmail(),
                            u.getPassword(),
                            u.getDob(),
                            u.isAgreedTC(),
                            u.getSponsorId(),
                            u.getAuthtoken(),
                            u.getDeviceRegId(),
                            u.getRole(),
                            u.isActive()
                    );
                }
            }
            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                    Log.d(TAG, t.toString());
            }
        });
        return u;
    }*/


}
