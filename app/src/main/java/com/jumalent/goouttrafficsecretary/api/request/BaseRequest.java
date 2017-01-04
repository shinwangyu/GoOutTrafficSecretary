package com.jumalent.goouttrafficsecretary.api.request;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by seeroo_dev on 2016. 12. 8..
 */
public class BaseRequest
{
    //기본 URL (tago url을 넣으면 됨.
//    private static String baseURL = "https://dtcode.seeroo.info/tcode/";
    private static String baseURL = "https://api.github.com/";

    //Default RetroFit Setting
    protected static Object retrofit(Class<?> className){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(className);
    }


}
