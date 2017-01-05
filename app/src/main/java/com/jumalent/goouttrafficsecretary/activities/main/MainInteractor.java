package com.jumalent.goouttrafficsecretary.activities.main;

/**
 * Created by seeroo_dev on 2017. 1. 4..
 */
public interface MainInteractor {

    void onSelectedItem(int pos);


    //API method
    void onRequestMetroAPI(String stPoint, String enPoint, retrofit2.Callback callback);
    void onRequestBusAPI(String stPoint, String enPoint, retrofit2.Callback callback);
}
