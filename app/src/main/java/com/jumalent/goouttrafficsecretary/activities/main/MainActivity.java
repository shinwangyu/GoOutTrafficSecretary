package com.jumalent.goouttrafficsecretary.activities.main;

import android.content.Context;
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
import com.jumalent.goouttrafficsecretary.activities.map.MapActivity;
import com.jumalent.goouttrafficsecretary.activities.pathlist.PathListActivity;
import com.jumalent.goouttrafficsecretary.utils.L;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends com.jumalent.goouttrafficsecretary.activities.BaseActivity implements MainView
{
    private static final String TAG = MainActivity.class.getSimpleName();

    private DrawerLayout m_DL_Menu = null;
    private MainPresenter mainPresenter = null;

    @Bind(R.id.test_metro_bt)
    Button test_metro_bt;

    @OnClick({R.id.test_metro_bt, R.id.test_bus_bt})
    void myOnclick(View view){
        switch (view.getId()) {
            case R.id.test_metro_bt:
                L.e("test_button clicked");
                mainPresenter.requestMetroAPI("start point", "end point");
                break;

            case R.id.test_bus_bt:
                mainPresenter.requestBusAPI("start point", "end point");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        L.e(TAG, "mainOnCreate ---------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ac_Container = this;

        //set layout
        initLayout();



        mainPresenter = new MainPresenterImpl(this);
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
                    //지도 보기
                    mainPresenter.requestGotoMapActivity();
                break;

                case R.id.nav_path:
                {
                    //저장된 출퇴근 경로 List 화면
                    mainPresenter.requestGotoMapActivity();
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
    @Override
    public void doStartMapActivity(){
        baseIntent = new Intent(this, MapActivity.class);
        startActivity(baseIntent);
    }

    @Override
    public void doStartPathListActivity(){
        baseIntent = new Intent(this, PathListActivity.class);
        startActivity(baseIntent);
    }


    @Override
    public void getSelectedItem(int pos) {
    }

    @Override
    public void showProgress() {
        startProgress();
    }

    @Override
    public void hideProgress() {
        stopProgress();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
