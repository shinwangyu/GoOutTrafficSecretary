package com.jumalent.goouttrafficsecretary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.jumalent.goouttrafficsecretary.R;
import com.jumalent.goouttrafficsecretary.api.request.APIListRequest;
import com.jumalent.goouttrafficsecretary.api.response.ReqAppInfoResponse;
import com.jumalent.goouttrafficsecretary.api.response.User;
import com.jumalent.goouttrafficsecretary.utils.L;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends com.jumalent.goouttrafficsecretary.activities.BaseActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();
    private DrawerLayout    m_DL_Menu = null;

    @Bind(R.id.test_button)
    Button test_button;

    @OnClick(R.id.test_button)
    void myOnclick(View view){
        switch (view.getId()) {
            case R.id.test_button:
                L.e("test_button clicked");

                //test request API
//                doRequestRetroAPI();


                doExampleRequestGitHubService();

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        L.e("MainActivity ---------------------------");
        ac_Container = this;

        //butterKnife setting
        ButterKnife.bind(this);

        initLayout();
    }


    protected void initLayout()
    {
        final Toolbar TB_TOP = getToolbar(R.id.toolbar);
        setSupportActionBar(TB_TOP);

        final FloatingActionButton FAB_BOTTOM = getFloatingActionButton(R.id.fab);
        FAB_BOTTOM.setOnClickListener(m_FloatBottomClickListener);

        m_DL_Menu = getDrawerLayout(R.id.drawer_layout);

        final ActionBarDrawerToggle ABDT_MENU = new ActionBarDrawerToggle( this, m_DL_Menu, TB_TOP,
                                                                            R.string.navigation_drawer_open,
                                                                            R.string.navigation_drawer_close);
        m_DL_Menu.setDrawerListener(ABDT_MENU);
        ABDT_MENU.syncState();

        final NavigationView NV_MENU = getNavigationView(R.id.nav_view);
        NV_MENU.setNavigationItemSelectedListener(m_NavigationItemClickListener);
    }

    /**
     * 네비게이션 아이템 클릭 리스너
     */
    private NavigationView.OnNavigationItemSelectedListener m_NavigationItemClickListener = new NavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.nav_map:
                    doStartMapActivity();
                break;

                case R.id.nav_path:
                {
                    L.e("nav_path clicked--");
                }
                break;

                case R.id.nav_manage:
                {

                }
                break;

                case R.id.nav_share:
                {

                }
                break;

                case R.id.nav_send:
                {

                }
                break;
            }

            m_DL_Menu.closeDrawer(GravityCompat.START);
            return true;
        }
    };

    /**
     * 하단 플롯팅 버틀 클릭 리스너
     */
    private View.OnClickListener m_FloatBottomClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            L.e("m_FloatBottomClickListener clicked");
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 맵뷰를 보여준다.
     * */
    private void doStartMapActivity(){
        Intent intent;
        intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }



    private void doRequestRetroAPI(){

        L.e("doRequestRetroAPIs ------");
        APIListRequest.tcodeApi().reqAppInfo("Zv/pqPhI+bNpBlxyl3DMLlz50w1Kp90jrtmiMKz3F2t2LHnPzeQksiepgMJOUduG4djJ7WXIeKJvNKvFC/jOriAIAVpK+ghv/hxxG+H2oGmN2ps1oBdYyTOt4WUuQGCAIwTYgBXZSTkSdTZoH8LB+uHjagKbT14D+MC5v5Hz7YI=",
                "sh000000000000000",
                "SH",
                "0b15020138c881e2",
                1,
                "22222222222222222222").enqueue(new Callback<ReqAppInfoResponse>() {
            @Override
            public void onResponse(Call<ReqAppInfoResponse> call, Response<ReqAppInfoResponse> response) {
                L.e("response.isSuccessful()    : " + response.isSuccessful());
                L.e("response.message()         : " + response.message());
                L.e("response.body()            : " + response.body());
                L.e("response.code()            : " + response.code());
            }

            @Override
            public void onFailure(Call<ReqAppInfoResponse> call, Throwable t) {
                L.e("onFailure -- ");
            }
        });

    }


    private void doExampleRequestGitHubService(){
        APIListRequest.testGitHubApi().getUser("octocat").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                L.e("response.isSuccessful()    : " + response.isSuccessful());
                L.e("response.message()         : " + response.message());
                L.e("response.body()            : " + response.body());
                L.e("response.code()            : " + response.code());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                L.e("onFailure -- ");
            }
        });
    }


    private void doExampleRequestRetroSampleAPI(){
//        APIListRequest.api().retroRequest("sample", "subway")
    }

}
