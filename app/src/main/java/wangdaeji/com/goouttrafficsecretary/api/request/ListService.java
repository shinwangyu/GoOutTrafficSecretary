package wangdaeji.com.goouttrafficsecretary.api.request;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import wangdaeji.com.goouttrafficsecretary.api.response.RetroResponse;

/**
 * Created by seeroo_dev on 2016. 12. 9..
 */
public class ListService extends BaseService{

    public static ListAPI api(){
        return (ListAPI) retrofit(ListAPI.class);
    }

    public interface ListAPI
    {
        @GET("/api/subway")
        Call<RetroResponse> retroRequest(
                @Path("KEY") String authkey,
                @Path("TYPE") String type,
                @Path("SERVICE") String service,
                @Path("START_INDEX") int start_index,
                @Path("END_INDEX") int end_index,
                @Path("statnNm") String statnNm
        );

    }



}
