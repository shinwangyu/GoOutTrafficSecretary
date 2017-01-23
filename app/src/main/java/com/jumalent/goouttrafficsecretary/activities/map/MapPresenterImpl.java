package com.jumalent.goouttrafficsecretary.activities.map;

import com.gun0912.tedpermission.PermissionListener;

/**
 * Created by seeroo_dev on 2017. 1. 6..
 */
public class MapPresenterImpl implements MapPresenter {

    private static final String TAG = MapPresenter.class.getSimpleName();
    MapInteractor   mInteractor  = null;
    MapView         mMapview     = null;
    GoogleMapManager mGMManager  = null;


    public MapPresenterImpl(MapView mapview){
        this.mMapview = mapview;
        mInteractor = new MapInteractorImpl();
        mGMManager = new GoogleMapManager(mapview.getContext());
    }

    @Override
    public GoogleMapManager getGoogleMapManager() {
        return mGMManager;
    }

    @Override
    public void requestLocationPermission(PermissionListener listener) {
        mGMManager.requestLocationPermission(listener);
    }

    @Override
    public void requestGpsPermission(PermissionListener listener) {
        mGMManager.requestGpsPermission(listener);
    }

    @Override
    public void googleClientConnection(){
        mGMManager.googleClientConnection();

    }

    @Override
    public void googleClientDisConnection(){
        mGMManager.googleClientDisConnection();

    }

    @Override
    public void googleClientDestroy(){
        mGMManager.googleClientDestroy();
    }

}
