package com.jumalent.goouttrafficsecretary;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;

import com.jumalent.goouttrafficsecretary.utils.L;
import com.jumalent.goouttrafficsecretary.utils.MyPreferenceData;


/**
 * Created by seeroo_dev on 2016. 12. 7..
 */
public class TrafficApplication extends Application {
    private static String TAG = TrafficApplication.class.getSimpleName();

    /** release/debug FALG */
    public static boolean DEBUG     = true;

    //Temp value code
    private static String mAppID                   = null;
    private static String mUserName                = null;
    private static String mUserPassword            = null;
    private static String mUserAction              = null;
    private static boolean mRegisteredAccount      = false;    //서비스 가입 여부

    private static TrafficApplication get(Context context) {
        return (TrafficApplication) context.getApplicationContext();
    }

    public static TrafficApplication create(Context context) {
        return TrafficApplication.get(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //build flag에 따라 DEBUG값을 설정해 준다. release빌드 : false, debug빌드: true
        DEBUG = (0 != (getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE));

        L.LOG_ON = DEBUG;


        //회원가입여부 저장
        setRegisteredAccount();

    }


    /**
     * 서비스 가입여부 저장. (Global value)
     */
    private void setRegisteredAccount(){
        MyPreferenceData mpd = new MyPreferenceData(this);
        if(TextUtils.isEmpty(mpd.getUserAccount()) ){
            mRegisteredAccount = true;
        }else{
            mRegisteredAccount = false;
        }
    }


    public static boolean ismRegisteredAccount() {
        return mRegisteredAccount;
    }
}



