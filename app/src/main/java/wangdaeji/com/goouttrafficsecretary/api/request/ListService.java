package wangdaeji.com.goouttrafficsecretary.api.request;

import retrofit2.Call;
import retrofit2.http.POST;
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
        @POST("/api/subway/{authkey}/{type}/{service}/{start_index}/{end_index}/{statnNm}/")
        Call<RetroResponse> retroRequest(
                @Path("authkey") String authkey,
                @Path("type") String type,
                @Path("service") String service,
                @Path("start_index") int start_index,
                @Path("end_index") int end_index,
                @Path("statnNm") String statnNm
        );

    }



}
