package wangdaeji.com.goouttrafficsecretary.activities;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wangdaeji.com.goouttrafficsecretary.R;
import wangdaeji.com.goouttrafficsecretary.api.request.ListService;
import wangdaeji.com.goouttrafficsecretary.api.response.RetroResponse;
import wangdaeji.com.goouttrafficsecretary.utils.L;

public class MainActivity extends BaseActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();

    private DrawerLayout    m_DL_Menu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
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
                    .setAction("if need, do Custom", null).show();

            //test request API
            doRequestRetroAPI();
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


    /**
     * 지하철 정보를 불러온다.
     */
    private void doRequestRetroAPI(){

        ListService.api().retroRequest("sample", "json", "realtimeStationArrival", 0, 5, "신림").enqueue(new Callback<RetroResponse>() {
            @Override
            public void onResponse(Call<RetroResponse> call, Response<RetroResponse> response) {
                if(response != null && response.isSuccessful() && response.body() != null){
                    L.e("response = " + response.body());
                }
                else{
                    L.e("response is null");
                }
            }

            @Override
            public void onFailure(Call<RetroResponse> call, Throwable t) {
                L.e("response Failed t : " + t.getMessage());
            }
        });

//        http://swopenAPI.seoul.go.kr/api/subway/(인증키)/xml/realtimeStationArrival/0/5/서울
    }

}
