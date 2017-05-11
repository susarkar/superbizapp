package com.ss.app.superbiz.rest.api;

import com.ss.app.superbiz.model.ChallengeApp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Susanta on 01-05-2017.
 */

public interface ChallengeApi {
    @GET("/all_challenges")
    Call<List<ChallengeApp>> getChallengeApps();
}
