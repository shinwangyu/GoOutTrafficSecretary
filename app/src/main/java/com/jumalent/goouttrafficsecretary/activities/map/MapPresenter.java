package com.jumalent.goouttrafficsecretary.activities.map;

import com.gun0912.tedpermission.PermissionListener;

/**
 * Created by seeroo_dev on 2017. 1. 6..
 */
public interface MapPresenter {
    GoogleMapManager getGoogleMapManager();
    void requestLocationPermission(PermissionListener listener);
    void requestGpsPermission(PermissionListener listener);
    void googleClientConnection();
    void googleClientDisConnection();
    void googleClientDestroy();
}
