package com.jumalent.goouttrafficsecretary.activities.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jumalent.goouttrafficsecretary.R;
import com.jumalent.goouttrafficsecretary.activities.BaseActivity;
import com.jumalent.goouttrafficsecretary.activities.login.LogInActivity;

public class IntroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //progressbar start
        if(!cpd.isShowing()) cpd.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //temp Code 1초후 로그인 화면으로 이동.

                //추 후 서버 통신 business로직 설계
                gotoLogInActivity();

            }
        }, 1000);

    }

    private void gotoLogInActivity() {
        baseIntent = new Intent(this, LogInActivity.class);
        startActivity(baseIntent);
        finish();
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        cpd.dismiss();
    }



}
