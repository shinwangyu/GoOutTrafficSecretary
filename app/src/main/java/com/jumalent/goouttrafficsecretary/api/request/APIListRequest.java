package com.jumalent.goouttrafficsecretary.api.request;

import com.jumalent.goouttrafficsecretary.api.response.MetroResponse;
import com.jumalent.goouttrafficsecretary.api.response.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by seeroo_dev on 2016. 12. 9..
 */
public class APIListRequest extends BaseRequest {

    /**
     * API요청 interface 만드는 법
     * 1. interface 정의
     * 2. interface를 호출 할 수 있는 static method 정의
     * 3. interface에 전송 방식 설정 : @GET or @POST
     */



    /**
     * 지하철 정보 받아오는 api
     * @return
     */
    public static MetroDataRequest api(){
        return (MetroDataRequest) retrofit(MetroDataRequest.class);
    }
    public interface MetroDataRequest
    {
        @GET("api/subway/{authkey}/{type}/{service}/{start_index}/{end_index}/{statnNm}/")
        Call<MetroResponse> metroRequest(
                @Path("authkey") String authkey,
                @Path("type") String type,
                @Path("service") String service,
                @Path("start_index") int start_index,
                @Path("end_index") int end_index,
                @Path("statnNm") String statnNm
        );
    }




    /**
     * gitHub값 받아오는 api (TEST용)
     * @return
     */
    public static GitHubService testGitHubApi(){
        return (GitHubService) retrofit(GitHubService.class);
    }
    public interface GitHubService{
        @GET("users/{user}/repos")
        Call<User> getUser(@Path("user") String username);
    }
//
//
//
//    /**
//     * tcode서버 앱정보 받아오는 API (TEST용)
//     * @return
//     */
//    public static tcodeRequest tcodeApi(){
//        return (tcodeRequest) retrofit(tcodeRequest.class);
//    }
//    public interface tcodeRequest{
//        @POST(value = "api/reqAppInfo")
//        @FormUrlEncoded
//        Call<ReqAppInfoResponse> reqAppInfo(@Field("appID") String appID,
//                                            @Field("appKeyID") String appKeyID,
//                                            @Field("cardsaID") String cardsaID,
//                                            @Field("deviceID") String deviceID,
//                                            @Field("deviceType") int deviceType,
//                                            @Field("encClnn") String encClnn);
//
//    }


}
