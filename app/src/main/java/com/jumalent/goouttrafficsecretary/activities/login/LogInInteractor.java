package com.jumalent.goouttrafficsecretary.activities.login;

import android.content.Context;

/**
 * Created by seeroo_dev on 2017. 1. 4..
 */
public interface LogInInteractor {

    interface OnLoginFinishedListener {
        void onUsernameError();
        void onPasswordError();
        void onInvalidateError();
        void onSuccess();
    }

    void login(Context context, String username, String password, OnLoginFinishedListener listener);
}
