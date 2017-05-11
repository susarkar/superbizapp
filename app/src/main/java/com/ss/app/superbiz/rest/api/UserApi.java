package com.ss.app.superbiz.rest.api;

import com.ss.app.superbiz.model.ChallengeApp;
import com.ss.app.superbiz.model.LoginCredential;
import com.ss.app.superbiz.model.UserDetails;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Susanta on 01-05-2017.
 */

public interface UserApi {
 /*   @FormUrlEncoded
    @POST("/signin")
    Call<UserDetails> login(
            @Field("mobileNumber") String mobileNumber,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("/signup")
    Call<UserDetails> signup(
            @Field("id") String id,
            @Field("mobileNumber") String mobileNumber,
            @Field("userId") String userId,
            @Field("name")  String name,
            @Field("email")  String email,
            @Field("password")  String password,
            @Field("dob") Date dob,
            @Field("agreedTC")  boolean agreedTC,
            @Field("sponsorId")  String sponsorId,
            @Field("authToken")  String authToken,
            @Field("deviceRegId")  String deviceRegId,
            @Field("country")  String country,
            @Field("role")  String role
    );*/
    @POST("/signin")
    Call<UserDetails> login(@Body LoginCredential loginCredential);

    @POST("/signup")
    Call<UserDetails> signup(@Body UserDetails userDetails);

    @GET("/all_challenges")
    Call<List<ChallengeApp>> getChallengeApps();
}
