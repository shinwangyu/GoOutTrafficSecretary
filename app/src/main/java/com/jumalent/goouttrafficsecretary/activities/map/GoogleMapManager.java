package com.jumalent.goouttrafficsecretary.activities.map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.jumalent.goouttrafficsecretary.R;
import com.jumalent.goouttrafficsecretary.utils.L;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by seeroo_dev on 2017. 1. 6..
 */
public class GoogleMapManager implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback, LocationListener {
    private static final String TAG = GoogleMapManager.class.getSimpleName();
    private GoogleMap mGoogleMap = null;
    private GoogleApiClient mGoogleApiClient = null;
    private Context mapContext = null;
    private boolean setGPS = false;

    public GoogleMapManager(Context context) {
        this.mapContext = context;
        mGoogleApiClient = getBuildGoogleApiClient();
    }

    synchronized GoogleApiClient getBuildGoogleApiClient() {
        try {

            GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(mapContext)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            return mGoogleApiClient;
        } catch (Exception e) {
            L.e("buildGoogleApiClient" + e.getMessage());
        }

        return null;
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //성공적으로 GoogleApiClient 객체 연결되었을 때 실행

        LocationRequest mLocationRequest = new LocationRequest();
        //mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);

        if (ActivityCompat.checkSelfPermission(mapContext, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(mapContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            L.d(TAG, "onConnected " + "getLocationAvailability mGoogleApiClient.isConnected()=" + mGoogleApiClient.isConnected());
            if (!mGoogleApiClient.isConnected()) mGoogleApiClient.connect();


            // LocationAvailability locationAvailability = LocationServices.FusedLocationApi.getLocationAvailability(mGoogleApiClient);

            if (setGPS && mGoogleApiClient.isConnected())//|| locationAvailability.isLocationAvailable() )
            {
                L.d(TAG, "onConnected " + "requestLocationUpdates");
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

                Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (location == null) return;

                //현재 위치에 마커 생성
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(mapContext.getString(R.string.now_loacation));
                mGoogleMap.addMarker(markerOptions);

                //지도 상에서 보여주는 영역 이동
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            }

        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //구글 플레이 서비스 연결이 해제되었을 때, 재연결 시도
        L.d(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        L.d(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }

    @Override
    public void onLocationChanged(Location location) {
        String errorMessage = null;

        mGoogleMap.clear();

        //현재 위치에 마커 생성
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(mapContext.getResources().getString(R.string.now_loacation));

        //지도 상에서 보여주는 영역 이동
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mGoogleMap.getUiSettings().setCompassEnabled(true);

        //지오코더 GPS를 주소로 변환
        Geocoder gcoder = new Geocoder(mapContext, Locale.getDefault());

        //지오코더를 사용하여 주소를 찾는다.
        List<Address> addresses = null;

        try {
            addresses = gcoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException | IllegalArgumentException ioException) {
            L.e(TAG, "onLocationChaged Err : " + ioException.getMessage());
            errorMessage = mapContext.getResources().getString(R.string.err_geocoder);
        }

        //Handle case where no address was found.
        if (addresses == null || addresses.size() == 0) {
            if (errorMessage == null) {
                errorMessage = mapContext.getResources().getString(R.string.err_not_found_address);
            }
        } else {
            android.location.Address address = addresses.get(0);
            errorMessage = address.getAddressLine(0);
        }

        Toast.makeText(mapContext, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        LatLng SEOUL = new LatLng(37.56, 126.97);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        //현재위치 버튼 활성화
        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                L.d(TAG, "onMapLoaded");
                if (ActivityCompat.checkSelfPermission(mapContext, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(mapContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                    L.e("Permission not allowed.....");
                    return;
                }

                mGoogleMap.setMyLocationEnabled(true);
            }
        });
    }



    public void googleClientConnection()
    {
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    public void googleClientDisConnection(){
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    public void googleClientDestroy(){
        L.d( TAG, "GoogleMapManager MyDestroy");
        if (mGoogleApiClient != null) {
            mGoogleApiClient.unregisterConnectionCallbacks(this);
            mGoogleApiClient.unregisterConnectionFailedListener(this);

            if (mGoogleApiClient.isConnected()) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            }

            mGoogleApiClient.disconnect();
            mGoogleApiClient = null;
        }
    }



    public boolean requestIsGpsAllow() {
        LocationManager locationManager = (LocationManager) mapContext.getSystemService(mapContext.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // GPS ON
            return true;
        }

        //GPS OFF
        return false;
    }


    /**
     * 위치 정보 권한 요청
     * @param listener
     */
    public void requestLocationPermission(PermissionListener listener) {
        L.d(TAG, "checkPermission ( " + android.Manifest.permission.ACCESS_FINE_LOCATION + " )");

        new TedPermission(mapContext)
                .setPermissionListener(listener)
                .setRationaleMessage(R.string.location_permission_info)
                .setDeniedMessage(R.string.location_permission_disagree_info)
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }


    /**
     * GPS 권한 요청
     */
    public void requestGpsPermission(PermissionListener listener){
        L.d(TAG, "checkPermission ( " + android.Manifest.permission.ACCESS_FINE_LOCATION + " )");

        new TedPermission(mapContext)
                .setPermissionListener(listener)
                .setRationaleMessage(R.string.location_permission_info)
                .setDeniedMessage(R.string.location_permission_disagree_info)
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check();

    }





}
