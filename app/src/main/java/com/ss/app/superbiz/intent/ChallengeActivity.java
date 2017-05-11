package com.ss.app.superbiz.intent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ss.app.superbiz.R;
import com.ss.app.superbiz.SessionManager;
import com.ss.app.superbiz.model.ChallengeApp;
import com.ss.app.superbiz.rest.api.ApiClient;
import com.ss.app.superbiz.rest.api.ChallengeApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengeActivity extends AppCompatActivity {
    private static final String TAG = ChallengeActivity.class.getSimpleName();
    SessionManager session;
    private List<ChallengeApp> appList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        session = new SessionManager(getApplicationContext());
        Log.d(TAG, "is Challenge complete  = " + session.isChallengeComplete());
        if(session.isChallengeComplete()){
            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
              i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
        initViews();
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        loadChallengeApp();
    }

    private void loadChallengeApp(){
        ChallengeApi challengeApi = ApiClient.createApi(ChallengeApi.class);
        Call<List<ChallengeApp>> call = challengeApi.getChallengeApps();
        call.enqueue(new Callback<List<ChallengeApp>>() {
            @Override
            public void onResponse(Call<List<ChallengeApp>>call, Response<List<ChallengeApp>> response) {
                appList = response.body();
                ChallengeAppAdapter adapter = new ChallengeAppAdapter(appList);
                recyclerView.setAdapter(adapter);

                Log.d(TAG, "Number of app received: " + appList.size());
            }

            @Override
            public void onFailure(Call<List<ChallengeApp>>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }


    public class ChallengeAppAdapter extends RecyclerView.Adapter<ChallengeAppAdapter.ViewHolder> {
        private List<ChallengeApp> appList;

        public ChallengeAppAdapter(List<ChallengeApp> appList) {
            this.appList = appList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.challenge_app_item, parent, false);
            return new ViewHolder(view);        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //  holder.tv_appid.setText(appList.get(position).getId());
            holder.tv_appName.setText(appList.get(position).getAppName());
            holder.appPackageName = appList.get(position).getPackageName();
            if (isAppInstalled(appList.get(position).getPackageName())) {
                holder.button.setText("DONE");
                holder.button.setEnabled(false);
            }else{
                holder.button.setText("INSTALL");
                holder.button.setEnabled(true);
            }
        }

        @Override
        public int getItemCount() {
            return appList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            String appPackageName;
            TextView tv_appName;
            Button button;
            public ViewHolder(View itemView) {
                super(itemView);
                tv_appName = (TextView)itemView.findViewById(R.id.appName);

                button = (Button) itemView.findViewById(R.id.install);
                button.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {

                    installApp(appPackageName);

            }
        }
    }
    private void installApp(String  appPackageName){
        Log.d(TAG, "Package Name to Install: " + appPackageName);
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
        if(isAllAppInstalled()) {
            session.setChallengeComplete(true);
            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }else {
            loadChallengeApp();
        }
    }
    private boolean isAllAppInstalled(){
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
