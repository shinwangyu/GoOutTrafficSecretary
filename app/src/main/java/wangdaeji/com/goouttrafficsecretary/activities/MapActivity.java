package wangdaeji.com.goouttrafficsecretary.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import wangdaeji.com.goouttrafficsecretary.R;
import wangdaeji.com.goouttrafficsecretary.utils.L;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener{
    private static final String TAG = MapActivity.class.getSimpleName();

    private static final int REQUEST_CODE_GPS       = 2001;
    private static final int REQUEST_CODE_LOCATION  = 2000;

    private GoogleMap       mGoogleMap;
    private GoogleApiClient mGoogleApiClient = null;
    private MapFragment     mapFragment;
    private boolean         setGPS = false;
    private EditText        search_et = null;

    protected synchronized void buildGoogleApiClient() {
        try {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        } catch (Exception e) {
            L.e("buildGoogleApiClient" + e.getMessage());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //map Fragment
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fm);
        mapFragment.getMapAsync(this);

        //my set widget
        search_et = (EditText)findViewById(R.id.search_et);
        search_et.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Toast.makeText(MapActivity.this, search_et.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        LatLng SEOUL = new LatLng(37.56, 126.97);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        //현재위치 버튼 활성화
        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback()
        {
            @Override
            public void onMapLoaded()
            {
                L.d(TAG, "onMapLoaded");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    // os 6.0이상 퍼미션 체크
                    checkLocationPermission();
                }
                else {
                    if (checkGPSAllowed()) {
                        L.d(TAG, "onMapLoaded");
                        showGPSDisabledAlertToUser();
                    }

                    if (mGoogleApiClient == null) {
                        buildGoogleApiClient();
                    }

                    if (ActivityCompat.checkSelfPermission(MapActivity.this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(MapActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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


    public boolean checkLocationPermission()
    {
        L.d(TAG, "checkLocationPermission");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                //퍼미션 요청을 위해 UI를 보여줘야 하는지 검사
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                    //Prompt the user once explanation has been shown;
                    requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_LOCATION);

                } else {
                    //UI보여줄 필요 없이 요청
                    requestPermissions(new String[]{
                            android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_LOCATION);
                }

                return false;
            }
            else
            {
                L.d( TAG, "checkLocationPermission"+"이미 퍼미션 획득한 경우");

                if (!checkGPSAllowed() && !setGPS)
                {
                    L.d(TAG, "checkLocationPermission Version >= M");
                    showGPSDisabledAlertToUser();
                }

                if (mGoogleApiClient == null) {
                    L.d( TAG, "checkLocationPermission "+"mGoogleApiClient==NULL");
                    buildGoogleApiClient();
                }
                else {
                    L.d(TAG, "checkLocationPermission " + "mGoogleApiClient!=NULL");
                }

                if ( mGoogleApiClient.isConnected() ) {
                    L.d(TAG, "checkLocationPermission" + "mGoogleApiClient 연결되 있음");
                }
                else{
                    L.d( TAG, "checkLocationPermission"+"mGoogleApiClient 끊어져 있음");
                }

                mGoogleApiClient.reconnect();//이미 연결되어 있는 경우이므로 다시 연결
                mGoogleMap.setMyLocationEnabled(true);
            }
        }

        return true;
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //성공적으로 GoogleApiClient 객체 연결되었을 때 실행

        LocationRequest mLocationRequest = new LocationRequest();
        //mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);


        if (ActivityCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            L.d( TAG, "onConnected " + "getLocationAvailability mGoogleApiClient.isConnected()="+mGoogleApiClient.isConnected() );
            if ( !mGoogleApiClient.isConnected()  ) mGoogleApiClient.connect();


            // LocationAvailability locationAvailability = LocationServices.FusedLocationApi.getLocationAvailability(mGoogleApiClient);

            if ( setGPS && mGoogleApiClient.isConnected() )//|| locationAvailability.isLocationAvailable() )
            {
                L.d( TAG, "onConnected " + "requestLocationUpdates" );
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

                Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if ( location == null ) return;

                //현재 위치에 마커 생성
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(getString(R.string.now_loacation));
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
    public void onLocationChanged(Location location) {
        String errorMessage = null;

        mGoogleMap.clear();

        //현재 위치에 마커 생성
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(getResources().getString(R.string.now_loacation));

        //지도 상에서 보여주는 영역 이동
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mGoogleMap.getUiSettings().setCompassEnabled(true);

        //지오코더 GPS를 주소로 변환
        Geocoder gcoder = new Geocoder(this, Locale.getDefault());

        //지오코더를 사용하여 주소를 찾는다.
        List<Address> addresses = null;

        try {
            addresses = gcoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException | IllegalArgumentException ioException) {
            L.e(TAG, "onLocationChaged Err : " + ioException.getMessage());
            errorMessage = getResources().getString(R.string.err_geocoder);
        }

        //Handle case where no address was found.
        if (addresses == null || addresses.size() == 0) {
            if (errorMessage == null) {
                errorMessage = getResources().getString(R.string.err_not_found_address);
            }
        } else {
            android.location.Address address = addresses.get(0);
            errorMessage = address.getAddressLine(0);
        }

        Toast.makeText(MapActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        L.d(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }



    public boolean checkGPSAllowed() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // GPS ON
            return true;
        }

        //GPS OFF
        return false;
    }


    //GPS 활성화를 위한 다이얼로그
    private void showGPSDisabledAlertToUser()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(getString(R.string.gps_not_allow_info))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.setting), new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(callGPSSettingIntent, REQUEST_CODE_GPS);
                    }
                });

        alertDialogBuilder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
                finishProcess();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }



    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);

        switch (requestCode) {
            case  REQUEST_CODE_GPS:
                //GPS 활성화를 위한 다이얼로그의 결과 처리

                if ( checkGPSAllowed())
                {
                    mapFragment.getMapAsync(MapActivity.this);
                }else{
                    //현재 화면 닫기
                    finishProcess();
                }
                break;
        }
    }

    private void finishProcess()
    {
        finish();
    }



    @Override
    protected void onStart() {
        super.onStart();

        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {

        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }


    @Override
    public void onPause() {
        if ( mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }

        super.onPause();
    }

    @Override
    protected void onDestroy() {

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

        super.onDestroy();
    }


}
