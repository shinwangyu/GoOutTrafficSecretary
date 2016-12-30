package com.jumalent.goouttrafficsecretary;

import android.app.Application;
import android.content.Context;

import com.jumalent.goouttrafficsecretary.utils.L;


/**
 * Created by seeroo_dev on 2016. 12. 7..
 */
public class TrafficApplication extends Application {

    private static String TAG = TrafficApplication.class.getSimpleName();

    /** release/debug FALG */
    public static final boolean DEBUG = true;

    private String mUserName;
    private String mUserPassword;
    private String mUserAction;

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
//        DEBUG = (0 != (getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE));

        L.LOG_ON = DEBUG;

    }







}



