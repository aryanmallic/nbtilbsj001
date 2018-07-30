package com.app.mashkabari.utility;

import android.app.Application;

import com.facebook.accountkit.AccountKit;
import com.google.firebase.FirebaseApp;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AccountKit.initialize(getApplicationContext());
    }
}