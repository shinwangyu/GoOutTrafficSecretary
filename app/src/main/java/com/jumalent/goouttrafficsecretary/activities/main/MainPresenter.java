package com.jumalent.goouttrafficsecretary.activities.main;

/**
 * Created by seeroo_dev on 2017. 1. 4..
 */
public interface MainPresenter {

    /**
     * 시작지 도착지별 지하철 노선 정보 요청
     * @param strPoint
     * @param endPoint
     */
    void doRequestMetro(String strPoint, String endPoint);

    /**
     * 시작지 도착지별 버스 노선 정보 요청
     * @param strPoint
     * @param endPoint
     */
    void doRequestBus(String strPoint, String endPoint);




    void onDestroy();
}
