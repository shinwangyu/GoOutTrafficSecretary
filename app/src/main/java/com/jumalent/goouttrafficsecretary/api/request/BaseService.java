package com.jumalent.goouttrafficsecretary.api.request;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by seeroo_dev on 2016. 12. 8..
 */
public class BaseService
{
    private static String baseURL = "https://dtcode.seeroo.info/tcode/";


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

//        ////////Log debugger//////////
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        OkHttpClient.Builder client = new OkHttpClient.Builder();
//        client.addInterceptor(interceptor);
//        ////////Log debugger//////////
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client.build())
//                .build();
//
//        return retrofit.create(className);
    }


}
