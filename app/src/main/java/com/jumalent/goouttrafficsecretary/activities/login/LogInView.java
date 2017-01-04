package com.jumalent.goouttrafficsecretary.activities.login;

import android.content.Context;

/**
 * Created by seeroo_dev on 2017. 1. 4..
 */
public interface LogInView {
    void setUsernameError();
    void setPasswordError();
    void setInvalidateError();
    void gotoMainActivity();
    void showProgress();
    void hideProgress();
    void setConfirmText(String text);
    void setVisibleCheckButton(int visible);
    void showSucceedToast();

    Context getContext();
}
