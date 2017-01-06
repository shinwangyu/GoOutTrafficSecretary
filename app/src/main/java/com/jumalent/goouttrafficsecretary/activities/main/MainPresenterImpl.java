package com.jumalent.goouttrafficsecretary.activities.main;

import com.jumalent.goouttrafficsecretary.api.response.BusResponse;
import com.jumalent.goouttrafficsecretary.api.response.MetroResponse;
import com.jumalent.goouttrafficsecretary.utils.L;

import retrofit2.Call;
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
    public void requestMetroAPI(String strPoint, String endPoint) {
        mainInteractor.onRequestMetroAPI(strPoint, endPoint, new retrofit2.Callback<MetroResponse>() {
            @Override
            public void onResponse(Call<MetroResponse> call, Response<MetroResponse> response) {
                L.e("response.isSuccessful()    : " + response.isSuccessful());
                L.e("response.message()         : " + response.message());
                L.e("response.body()            : " + response.body());
                L.e("response.code()            : " + response.code());

                if(response.isSuccessful()){


                }

                //화면 갱신이 필요할 경우 -> mainview 객체 사용



            }

            @Override
            public void onFailure(Call<MetroResponse> call, Throwable t) {
                L.e("onFailure -- ");

                //화면 갱신이 필요할 경우 -> mainview 객체 사용
            }
        });
    }

    @Override
    public void requestBusAPI(String strPoint, String endPoint) {
        mainInteractor.onRequestBusAPI(strPoint, endPoint, new retrofit2.Callback<BusResponse>() {
            @Override
            public void onResponse(Call<BusResponse> call, Response<BusResponse> response) {
                //화면 갱신이 필요할 경우 -> mainview 객체 사용
            }

            @Override
            public void onFailure(Call<BusResponse> call, Throwable t) {
                //화면 갱신이 필요할 경우 -> mainview 객체 사용
            }
        });
    }


    /**
     * TEST CODE
     */
    @Override
    public void requestGitHubAPI() {
//        APIListRequest.api().metroRequest("authKey", "type", "service", 1, 10, "신림").enqueue(callback);

//        APIListRequest.testGitHubApi().getUser("syyhukz").enqueue(new retrofit2.Callback<BusResponse>);
    }


    /**
     * 맵 화면 전환 요청
     */
    @Override
    public void requestGotoMapActivity() {
        //화면 이동 시 체크 사항이 있을 경우 여기서 체크...

        //맵화면으로 이동
        mainView.doStartMapActivity();
    }

    /**
     * 출퇴 경로 리스트 화면 전환 요청
     */
    @Override
    public void requestGotoPathListActivity(){
        //화면 이동 시 체크 사항이 있을 경우 여기서 체크....

        mainView.doStartPathListActivity();
    }



    @Override
    public void onDestroy() {

    }
}
