package com.jumalent.goouttrafficsecretary.activities.main;

import android.content.Context;

/**
 * Created by seeroo_dev on 2017. 1. 4..
 */
public interface MainView {
    void getSelectedItem(int pos);

    void showProgress();
    void hideProgress();

    Context getContext();
}
