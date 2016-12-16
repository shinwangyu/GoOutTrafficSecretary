package wangdaeji.com.goouttrafficsecretary.api.request;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by seeroo_dev on 2016. 12. 8..
 */
public class BaseService
{
    protected static Object retrofit(Class<?> className){

        ////////Log debugger//////////
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);
        ////////Log debugger//////////

        String baseURL = "https://api.github.com/";
//        String baseURL = "http://swopenAPI.seoul.go.kr/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(client.build())
                .build();

        return retrofit.create(className);
    }




}
