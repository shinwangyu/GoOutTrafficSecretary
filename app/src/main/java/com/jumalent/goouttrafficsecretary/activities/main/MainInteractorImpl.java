package com.jumalent.goouttrafficsecretary.activities.main;

import com.jumalent.goouttrafficsecretary.api.request.APIListRequest;
import com.jumalent.goouttrafficsecretary.utils.L;

/**
 * Created by seeroo_dev on 2017. 1. 4..
 */
public class MainInteractorImpl implements MainInteractor{

    @Override
    public void onSelectedItem(int pos) {
        //리스느 누른 position에 해당하는 값을 가져온다.

    }

    @Override
    public void onRequestMetroAPI(String stPoint, String enPoint, retrofit2.Callback callback) {
        L.e("onRequestMetroAPI");

        APIListRequest.api().metroRequest("authKey", "type", "service", 1, 10, "신림").enqueue(callback);
    }

    @Override
    public void onRequestBusAPI(String stPoint, String enPoint, retrofit2.Callback callback) {
        L.e("onRequestBusAPI");


        //        APIListRequest.tcodeApi().reqAppInfo("Zv/pqPhI+bNpBlxyl3DMLlz50w1Kp90jrtmiMKz3F2t2LHnPzeQksiepgMJOUduG4djJ7WXIeKJvNKvFC/jOriAIAVpK+ghv/hxxG+H2oGmN2ps1oBdYyTOt4WUuQGCAIwTYgBXZSTkSdTZoH8LB+uHjagKbT14D+MC5v5Hz7YI=",
//                "sh000000000000000",
//                "SH",
//                "0b15020138c881e2",
//                1,
//                "22222222222222222222").enqueue(callback);

    }
}
