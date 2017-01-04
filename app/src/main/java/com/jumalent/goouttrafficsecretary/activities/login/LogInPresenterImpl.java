package com.jumalent.goouttrafficsecretary.activities.login;

import android.view.View;
import android.widget.Toast;

import com.jumalent.goouttrafficsecretary.R;
import com.jumalent.goouttrafficsecretary.TrafficApplication;
import com.jumalent.goouttrafficsecretary.utils.L;

/**
 * Created by seeroo_dev on 2017. 1. 3..
 */
public class LogInPresenterImpl implements LogInPresenter , LogInInteractor.OnLoginFinishedListener {

    private LogInView loginView = null;
    private LogInInteractor logInInteractor = null;

    public LogInPresenterImpl(LogInView loginview) {
        this.loginView = loginview;
        logInInteractor = new LogInInteractorImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if(checkExistLoginView()){
            loginView.showProgress();
        }
        logInInteractor.login(loginView.getContext(), username, password, this);
    }


    /**
     * 중복체크 버튼 구현
     */
    @Override
    public void doIDCheck() {
        //추후 구현
        Toast.makeText(loginView.getContext(), "미 구현 버튼입니다.", Toast.LENGTH_SHORT).show();
    }

    /**
     * 신규/기존 사용자별로 화면 재구성
     * 중복 체크 버튼 활성화
     * 확인버튼의 Text값을 변경
     * @return
     */
    @Override
    public void setDisplay() {
        if(checkExistLoginView()){
            if(TrafficApplication.ismRegisteredAccount()){
                L.d("신규 등록");
                loginView.setVisibleCheckButton(View.VISIBLE);
                loginView.setConfirmText(loginView.getContext().getString(R.string.input_add));
            }else{
                L.d("기등록");
                loginView.setVisibleCheckButton(View.INVISIBLE);
                loginView.setConfirmText(loginView.getContext().getString(R.string.input_login));
            }
        }
    }

    @Override
    public void onUsernameError() {
        if(checkExistLoginView()) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if(checkExistLoginView()){
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onInvalidateError() {
        if(checkExistLoginView()){
            loginView.setInvalidateError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if(checkExistLoginView()){
            loginView.hideProgress();
            loginView.showSucceedToast();   //정상등록 toast 출력
            loginView.gotoMainActivity();
        }
    }

    @Override
    public void onDestroy() {
        loginView = null;
        logInInteractor = null;
    }

    /**
     * LoginView가 존재 할 경우 true 반환
     * @return boolean
     */
    private boolean checkExistLoginView(){
        if(loginView != null)
            return true;
        else {
            L.e("loginView is null.......");
            return false;
        }
    }
}
