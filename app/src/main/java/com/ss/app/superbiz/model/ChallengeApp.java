package com.ss.app.superbiz.model;

import android.graphics.drawable.Icon;

public class ChallengeApp {
    private String id;
    private String appName;
    private String packageName;
   // private Icon icon;

    public ChallengeApp(String appName, String packageName) {
        this.appName = appName;
        this.packageName = packageName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

/*    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }*/
}
