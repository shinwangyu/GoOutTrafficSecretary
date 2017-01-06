package com.jumalent.goouttrafficsecretary.activities.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jumalent.goouttrafficsecretary.R;
import com.jumalent.goouttrafficsecretary.TrafficApplication;
import com.jumalent.goouttrafficsecretary.activities.BaseActivity;
import com.jumalent.goouttrafficsecretary.activities.main.MainActivity;
import com.jumalent.goouttrafficsecretary.utils.L;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInActivity extends BaseActivity implements LogInView {

    /**
     * MVP Pattern 적용
     *
     * 1. LogInView를 상속받은 Activity는 view관련 처리  *User Action을 Activity가 받음.
     *
     * 2. LogInPresenterImpl은 LogInPresenter를 상속받아 Activity와 LogInPresenter를 연결해주는 연결자 역활을 함
     *
     * 3. LogInInteractorImpl은 LogInInteractor를 상속받아 business로직을 처리...   Ex) 로그인에서는 유저가 입력한 값 유효성등을 처리 등등.
     */

    private LogInPresenter loginPresenter = null;

    @Bind(R.id.login_id_et)
    EditText login_id_et;

    @Bind(R.id.login_pw_et)
    EditText login_pw_et;

    @Bind(R.id.login_confirm_bt)
    Button login_confirm_bt;

    @Bind(R.id.login_check_bt)
    Button login_check_bt;

    @OnClick({R.id.login_confirm_bt, R.id.login_check_bt})
    void myOnclick(View v){
        switch (v.getId())
        {
            case R.id.login_confirm_bt:
                loginPresenter.validateCredentials(login_id_et.getText().toString(), login_pw_et.getText().toString());
                break;

            case R.id.login_check_bt:
                loginPresenter.doIDCheck();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);

        loginPresenter = new LogInPresenterImpl(this);

        // 동적 화면 갱신
        loginPresenter.setDisplay();
    }



    @Override
    public void setUsernameError() {
        login_id_et.setError( getString(R.string.username_error) );
    }

    @Override
    public void setPasswordError() {
        login_pw_et.setError( getString(R.string.password_error) );
    }

    @Override
    public void setInvalidateError() {
        Toast.makeText(LogInActivity.this, R.string.check_confirm_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotoMainActivity() {
        L.e("Go to main activity");
        baseIntent = new Intent(this, MainActivity.class);
        startActivity(baseIntent);
        finish();
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
    public void setConfirmText(final String text) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                login_confirm_bt.setText(text);
            }
        });
    }

    @Override
    public void setVisibleCheckButton(int visible) {
        login_check_bt.setVisibility(visible);
    }

    @Override
    public void showSucceedToast() {
        if(TrafficApplication.ismRegisteredAccount()) {
            Toast.makeText(LogInActivity.this, R.string.succeed_login, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(LogInActivity.this, R.string.succeed_add, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

}
