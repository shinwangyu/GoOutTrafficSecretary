package com.jumalent.goouttrafficsecretary.activities.login;

/**
 * Created by seeroo_dev on 2017. 1. 2..
 */
public interface LogInPresenter
{
    void validateCredentials(String username, String password);
    void setDisplay();
    void doIDCheck();
    void onDestroy();


}