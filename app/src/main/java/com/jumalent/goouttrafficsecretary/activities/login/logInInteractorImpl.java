package com.jumalent.goouttrafficsecretary.activities.login;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.jumalent.goouttrafficsecretary.TrafficApplication;
import com.jumalent.goouttrafficsecretary.utils.MyPreferenceData;

/**
 * Created by seeroo_dev on 2017. 1. 4..
 */
public class LogInInteractorImpl implements LogInInteractor{


    @Override
    public void login(final Context context, final String username, final String password, final OnLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error = false;

                if(TextUtils.isEmpty(username)){
                    listener.onUsernameError();
                    error = true;
                }

                if(TextUtils.isEmpty(password)){
                    listener.onPasswordError();
                    error = true;
                }

                //아이디 비밀번호 체크
                if(validateAccount(context, username, password)){
                    listener.onInvalidateError();
                    error = true;
                }

                if(!error){
                    if(!TrafficApplication.ismRegisteredAccount()) {
                        //등록되어 있지 않을 경우에만 계정 등록
                        saveAccountValue(context, username, password);
                    }
                    listener.onSuccess();
                }
            }
        }, 2000);
    }

    private void saveAccountValue(Context context, String id, String pw){
        MyPreferenceData mpd = new MyPreferenceData(context);
        mpd.setUserAccount(id);
        mpd.setUserPassword(pw);
        mpd.commit();
    }

    /**
     * 기존 등록된 아이디, 비밀번호 체크
     * @param context
     * @param username
     * @param password
     * @return
     */
    private boolean validateAccount(Context context, String username, String password){

        if(TrafficApplication.ismRegisteredAccount()){
            //기존 고객은 체크
            MyPreferenceData mpd = new MyPreferenceData(context);
            if(username.equals(mpd.getUserAccount()) && password.equals(mpd.getUserPassword())){
                return true;
            }
        }
        else{
            //최초 등록 - by pass
            return true;
        }

        return false;
    }


}
