package com.jumalent.goouttrafficsecretary.activities.main;

import com.jumalent.goouttrafficsecretary.api.request.APIListRequest;
import com.jumalent.goouttrafficsecretary.api.response.ReqAppInfoResponse;
import com.jumalent.goouttrafficsecretary.utils.L;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seeroo_dev on 2017. 1. 2..
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mainView = null;
    MainInteractor mainInteractor = null;

    public MainPresenterImpl(MainView view){
        this.mainView = view;
        this.mainInteractor = new MainInteractorImpl();
    }


    @Override
    public void doRequestMetro(String strPoint, String endPoint) {

//        APIListRequest.testGitHubApi().getUser("octocat").enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                L.e("response.isSuccessful()    : " + response.isSuccessful());
//                L.e("response.message()         : " + response.message());
//                L.e("response.body()            : " + response.body());
//                L.e("response.code()            : " + response.code());
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                L.e("onFailure -- ");
//            }
//        });


        L.e("doRequestMetroAPIs ------");
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

    @Override
    public void doRequestBus(String strPoint, String endPoint) {

    }

    @Override
    public void onDestroy() {

    }
}
