package com.ss.app.superbiz.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.ss.app.superbiz.R;
import com.ss.app.superbiz.SessionManager;
import com.ss.app.superbiz.model.LoginCredential;
import com.ss.app.superbiz.model.UserDetails;
import com.ss.app.superbiz.rest.UserService;
import com.ss.app.superbiz.rest.api.ApiClient;
import com.ss.app.superbiz.rest.api.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private AwesomeValidation awesomeValidation;
    private EditText mobileNumber;
    private EditText password;
    private SessionManager sessionManager;
    private UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        mobileNumber = (EditText) findViewById(R.id.login_phone);
        awesomeValidation.addValidation(this, R.id.login_phone, "[0-9]{10}", R.string.mobileNumberError);
        password = (EditText)findViewById(R.id.login_pass);
        sessionManager = new SessionManager(getApplicationContext());
        userService = UserService.getUserService(getApplicationContext());
        if(sessionManager.isLoggedIn()){
           // sessionManager.setChallengeComplete(true);
            Intent ci = new Intent(this, ChallengeActivity.class);
            startActivity(ci);
            finish();
        }
    }
    public void login(View view) {
        if (awesomeValidation.validate()) {
            UserApi userApi = ApiClient.createApi(UserApi.class);
            Log.d(TAG, "Mobile number for for = " + mobileNumber.getText().toString());
            LoginCredential logincredential = new LoginCredential(mobileNumber.getText().toString(),password.getText().toString());
            Call<UserDetails> call = userApi.login(logincredential);
            call.enqueue(new Callback<UserDetails>() {
                @Override
                public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                    UserDetails userDetails = response.body();
                    if (userDetails !=null) {
                        sessionManager.createLoginSession(userDetails.getMobileNumber(),userDetails.getPassword());
                        Log.d(TAG, "registered and login successful show welcome activity");
                        Intent i = new Intent(getApplicationContext(), ChallengeActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<UserDetails> call, Throwable t) {
                    Log.d(TAG, t.toString());
                }
            });
        }
    }
    public void showRegisterActivity(View view){
        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(i);
        finish();
    }
}
