package com.ss.app.superbiz.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.ss.app.superbiz.R;
import com.ss.app.superbiz.SessionManager;
import com.ss.app.superbiz.model.UserDetails;
import com.ss.app.superbiz.rest.api.ApiClient;
import com.ss.app.superbiz.rest.api.UserApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private AwesomeValidation awesomeValidation;
    private EditText name ;
    private EditText email;
    private EditText mobile;
    private EditText password ;
    private EditText dob;
    private Spinner country;
    private CheckBox agree;
    private EditText sponsorId;

    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sessionManager = new SessionManager(getApplicationContext());
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        name = (EditText) findViewById(R.id.reg_name);
        email = (EditText) findViewById(R.id.reg_email);
        mobile = (EditText) findViewById(R.id.reg_phone);
        country = (Spinner) findViewById(R.id.reg_country);
        country.setAdapter(getCountryListAdapter());
        password = (EditText) findViewById(R.id.reg_pass);
        dob = (EditText) findViewById(R.id.reg_dob);
        sponsorId = (EditText) findViewById(R.id.reg_sponsor);
        agree = (CheckBox)findViewById(R.id.reg_agree);


    }

    public void signUp(View view){
        if (awesomeValidation.validate()) {
            UserDetails user = new UserDetails();
            user.setAgreedTC(agree.isChecked());
            user.setDob(dob.getText().toString());
            user.setEmail(email.getText().toString());
            user.setMobileNumber(mobile.getText().toString());
            user.setName(name.getText().toString());
            user.setPassword(password.getText().toString());
            user.setSponsorId(sponsorId.getText().toString());
            user.setCountry(country.getSelectedItem().toString());

           UserApi userApi = ApiClient.createApi(UserApi.class);
            Call<UserDetails> call = userApi.signup(user);
            call.enqueue(new Callback<UserDetails>() {
                @Override
                public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                    int statusCode = response.code();
                    UserDetails user = response.body();
                    Log.e(TAG, "User Details =  "+ user);
                    if(statusCode == 200){
                        sessionManager.createLoginSession(user.getMobileNumber(),user.getPassword());
                        Intent i = new Intent(getApplicationContext(), ChallengeActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                      //  i.putExtra("UserDetails", (Parcelable) user);
                        startActivity(i);
                        finish();
                    }
                }
                @Override
                public void onFailure(Call<UserDetails> call, Throwable t) {
                    Log.e(TAG, t.toString());
                }
            });

        }
    }
/*
    private void showChallengeActivity(UserDetails user){
        Intent i = new Intent(getApplicationContext(), ChallengeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     //   i.putExtra("UserDetails", (Parcelable) user);
        startActivity(i);
        finish();
    }*/
    private ArrayAdapter<String> getCountryListAdapter(){
        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        String country;
        for( Locale loc : locale ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);
        return new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, countries);

    }
}
