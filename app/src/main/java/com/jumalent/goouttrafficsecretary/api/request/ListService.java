package com.jumalent.goouttrafficsecretary.api.request;

import com.jumalent.goouttrafficsecretary.api.response.ReqAppInfoResponse;
import com.jumalent.goouttrafficsecretary.api.response.RetroResponse;
import com.jumalent.goouttrafficsecretary.api.response.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by seeroo_dev on 2016. 12. 9..
 */
public class ListService extends com.jumalent.goouttrafficsecretary.api.request.BaseService {

    public static ListAPI api(){
        return (ListAPI) retrofit(ListAPI.class);
    }

    public interface ListAPI
    {
        @GET("api/subway/{authkey}/{type}/{service}/{start_index}/{end_index}/{statnNm}/")
        Call<RetroResponse> retroRequest(
                @Path("authkey") String authkey,
                @Path("type") String type,
                @Path("service") String service,
                @Path("start_index") int start_index,
                @Path("end_index") int end_index,
                @Path("statnNm") String statnNm
        );

    }


    public static GitHubService testGitHubApi(){
        return (GitHubService) retrofit(GitHubService.class);
    }

    public interface GitHubService{
        @GET("users/{username}/repos")
        Call<User> getUser(@Path("username") String username);
    }


    public static tcodeRequest tcodeApi(){
        return (tcodeRequest) retrofit(tcodeRequest.class);
    }

    public interface tcodeRequest{
        @POST(value = "api/reqAppInfo")
        @FormUrlEncoded
        Call<ReqAppInfoResponse> reqAppInfo(@Field("appID") String appID,
                                            @Field("appKeyID") String appKeyID,
                                            @Field("cardsaID") String cardsaID,
                                            @Field("deviceID") String deviceID,
                                            @Field("deviceType") int deviceType,
                                            @Field("encClnn") String encClnn);

    }

}
