package com.jumalent.goouttrafficsecretary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by seeroo_dev on 2017. 1. 4..
 */
public class MyPreferenceData {
    /**
     * 앱 설치 이후 사용할 preference data (앱 삭제하기 전까지 유지)
     */

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    //Preference Data Constant List
    private final String APP_DATA       = "appData";
    private final String USER_ACCOUNT   = "user_account";
    private final String USER_PASSWORD  = "user_password";



    public MyPreferenceData(Context context){
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }


    //TEMP CODE
    public synchronized String getAppData() {
        return preferences.getString(APP_DATA, "");
    }

    public synchronized void setAppData(String data) {
        editor.putString(APP_DATA, data);
        commit();
    }
    //TEMP CODE


    /** 등록한 계정값 저장 */
    public synchronized String getUserAccount(){
        return preferences.getString(USER_ACCOUNT, "");
    }

    public synchronized void setUserAccount(String account){
        editor.putString(USER_ACCOUNT, account);
    }

    /** 등록한 비밀번호값 저장 */
    public synchronized String getUserPassword(){
        return preferences.getString(USER_PASSWORD, "");
    }

    public synchronized void setUserPassword(String password){
        editor.putString(USER_PASSWORD, password);
    }

    public void commit() { editor.commit(); }
    public void preferenceDataAllClear() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
    }
}
