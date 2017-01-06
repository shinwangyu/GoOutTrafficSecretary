package com.jumalent.goouttrafficsecretary.activities.map;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;
import com.gun0912.tedpermission.PermissionListener;
import com.jumalent.goouttrafficsecretary.R;
import com.jumalent.goouttrafficsecretary.activities.BaseActivity;
import com.jumalent.goouttrafficsecretary.utils.L;

import java.util.ArrayList;

public class MapActivity extends BaseActivity implements MapView{
    private static final String TAG = MapActivity.class.getSimpleName();

    private MapFragment     mapFragment         = null;
    private EditText        search_et           = null;
    private MapPresenter    presenter           = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //set layout
        initLayout();

        presenter = new MapPresenterImpl(this);


        startCheckPermission();
    }


    protected void initLayout() {
        //map Fragment
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fm);

        //my set widget
        search_et = (EditText)findViewById(R.id.search_et);
        search_et.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    Toast.makeText(MapActivity.this, search_et.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.googleClientConnection();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.googleClientConnection();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.googleClientDisConnection();
    }


    @Override
    public void onPause() {
        super.onPause();
        presenter.googleClientDisConnection();
    }

    private void finishProcess() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.googleClientDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }


    private void startCheckPermission(){
        presenter.requestLocationPermission(locationPermissionListener);
    }

    private void asyncMap(){
        //이거 하기 전에 권한 요청 ----------------
        mapFragment.getMapAsync(presenter.getGoogleMapManager());
    }


    PermissionListener locationPermissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            L.d(TAG, "위치 설정 성공");
            presenter.requestGpsPermission(GPSPermissionListener);
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            L.d(TAG, "위치 권한 설정 실패");
            finishProcess();
        }
    };


    PermissionListener GPSPermissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            L.d(TAG, "GPS 권한 설정 성공");
            asyncMap();
       }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            L.d(TAG, "GPS 권한 설정 실패");
            finishProcess();
        }
    };

}
