package wangdaeji.com.goouttrafficsecretary.api.request;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import wangdaeji.com.goouttrafficsecretary.api.response.RetroResponse;
import wangdaeji.com.goouttrafficsecretary.api.response.User;

/**
 * Created by seeroo_dev on 2016. 12. 9..
 */
public class ListService extends BaseService{

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


    public static GitHubService testApi(){
        return (GitHubService) retrofit(GitHubService.class);
    }

    public interface GitHubService{
        @GET("users/{username}/repos")
        Call<User> getUser(@Path("username") String username);
    }



}
