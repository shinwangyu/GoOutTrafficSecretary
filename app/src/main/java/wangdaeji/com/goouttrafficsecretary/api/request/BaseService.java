package wangdaeji.com.goouttrafficsecretary.api.request;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by seeroo_dev on 2016. 12. 8..
 */
public class BaseService
{
    protected static Object retrofit(Class<?> className){
        String baseURL = "http://swopenAPI.seoul.go.kr";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit.create(className);
    }




}
