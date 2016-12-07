package wangdaeji.com.goouttrafficsecretary.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import wangdaeji.com.goouttrafficsecretary.R;

/**
 * Created by seeroo_dev on 2016. 12. 7..
 * Google Map manager class
 */
public class GoogleMapManager implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener, OnMapReadyCallback {
    private static final String TAG = GoogleMapManager.class.getSimpleName();
    private static GoogleMapManager ourInstance = new GoogleMapManager();

    private Context mContext = null;
    private GoogleApiClient mGoogleApiClient = null;
    private GoogleMap mGoogleMap = null;
    boolean setGPS = false;

    public synchronized static GoogleMapManager getInstance() {
        return ourInstance;
    }

    private GoogleMapManager() {
    }

    private void setContext(Context _context) {
        mContext = _context;
    }


    protected synchronized void buildGoogleApiClient() {
        try {
            mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        } catch (Exception e) {
            L.e("buildGoogleApiClient" + e.getMessage());
        }
    }


    public boolean checkGPSAllowed() {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // GPS ON
            return true;
        }

        //GPS OFF
        return false;
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //성공적으로 GoogleApiClient 객체 연결되었을 때 실행
    }

    @Override
    public void onConnectionSuspended(int i) {
        //구글 플레이 서비스 연결이 해제되었을 때, 재연결 시도
        L.d(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        String errorMessage = null;

        mGoogleMap.clear();

        //현재 위치에 마커 생성
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(mContext.getResources().getString(R.string.now_loacation));

        //지도 상에서 보여주는 영역 이동
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mGoogleMap.getUiSettings().setCompassEnabled(true);

        //지오코더 GPS를 주소로 변환
        Geocoder gcoder = new Geocoder(mContext, Locale.getDefault());

        //지오코더를 사용하여 주소를 찾는다.
        List<android.location.Address> addresses = null;

        try {
            addresses = gcoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException | IllegalArgumentException ioException) {
            L.e(TAG, "onLocationChaged Err : " + ioException.getMessage());
            errorMessage = mContext.getResources().getString(R.string.err_geocoder);
        }

        //Handle case where no address was found.
        if (addresses == null || addresses.size() == 0) {
            if (errorMessage == null) {
                errorMessage = mContext.getResources().getString(R.string.err_not_found_address);
            }
        } else {
            android.location.Address address = addresses.get(0);
            errorMessage = address.getAddressLine(0);
        }

        Toast.makeText((Activity) mContext, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        L.d(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        LatLng SEOUL = new LatLng(37.56, 126.97);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));


        mGoogleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {

            @Override
            public void onMapLoaded() {
                L.d(TAG, "onMapLoaded");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    checkLocationPermission();

                    // os 6.0이상 퍼미션 체크
                } else {
                    if (checkGPSAllowed()) {
                        L.d(TAG, "onMapLoaded");
//                        showGPSDisabledAlertToUser();
                    }

                    if (mGoogleApiClient == null) {
                        buildGoogleApiClient();
                    }

                    if (ActivityCompat.checkSelfPermission(mContext,
                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }

                    mGoogleMap.setMyLocationEnabled(true);
                }

            }
        });



    }


    private void MyDestroy() {

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
}
