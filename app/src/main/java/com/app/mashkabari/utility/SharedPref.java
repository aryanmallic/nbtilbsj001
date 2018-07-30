package com.app.mashkabari.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPref {
    private SharedPreferences sharedPreferences;
    private static final String TAG = SharedPref.class.getSimpleName();
    Context mContext;
    public SharedPref() {
    }

    public SharedPref(Context mContext) {
        this.mContext = mContext;
        sharedPreferences = mContext.getSharedPreferences("MASHKABARI", Context.MODE_PRIVATE);
    }

    public void setLoginStatus(boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("loginStatus", value);
        editor.apply();
        Log.d(TAG, "setLoginStatus " + value);
    }

    public boolean getLoginStatus() {
        Log.d(TAG, "setLoginStatus " + sharedPreferences.getBoolean("loginStatus", false));
        if (sharedPreferences.getBoolean("loginStatus", false)) {
            return true;
        }
        return false;
    }

    public void setPhoneNum(String phone){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone", phone);
        editor.apply();
    }
    public String getPhoneNum() {
        Log.d(TAG,"KEY: "+sharedPreferences.getString("phone",""));
        return sharedPreferences.getString("phone","");
    }
}
