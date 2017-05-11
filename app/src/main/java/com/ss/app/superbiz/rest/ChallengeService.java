package com.ss.app.superbiz.rest;

import android.content.Context;
import android.util.Log;

import com.ss.app.superbiz.model.ChallengeApp;
import com.ss.app.superbiz.rest.api.ApiClient;
import com.ss.app.superbiz.rest.api.ChallengeApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Susanta on 01-05-2017.
 */

public class ChallengeService {
    private static ChallengeService chService;
    private ChallengeApi challengeApi;
    private ChallengeService(Context ctx) {
        challengeApi = ApiClient.createApi(ChallengeApi.class);
    }

    public static ChallengeService getChallengeService(Context ctx){
        if(chService ==null){
            chService = new ChallengeService(ctx);
        }
        return chService;
    }


    public  List<ChallengeApp> getAllChallenges() {
        final List<ChallengeApp> allChallenges = new ArrayList<ChallengeApp>();
        Call<List<ChallengeApp>> call = challengeApi.getChallengeApps();
        call.enqueue(new Callback<List<ChallengeApp>>() {
            @Override
            public void onResponse(Call<List<ChallengeApp>>call, Response<List<ChallengeApp>> response) {
                List<ChallengeApp>  allChallenges = response.body();
/*                for(ChallengeApp c : appList){
                    Log.d(TAG, "app : " + c.getAppName());
                }
                Log.d(TAG, "Number of app received: " + appList.size());
                */
            }

            @Override
            public void onFailure(Call<List<ChallengeApp>>call, Throwable t) {
                // Log error here since request failed
                Log.d(TAG, t.toString());
            }
        });
        return allChallenges;
    }
}
