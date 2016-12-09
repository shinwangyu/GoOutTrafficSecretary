package wangdaeji.com.goouttrafficsecretary.api.response;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

/**
 * Created by seeroo_dev on 2016. 12. 8..
 */
public class APIResponse {
    private String list_total_count;    //총 데이터 건수
    private String resultCode;          //요청결과 코드
    private String resultMsg;           //요청결과 메시지


    public String getList_total_count() {
        return list_total_count;
    }

    public void setList_total_count(String list_total_count) {
        this.list_total_count = list_total_count;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }


    @Override
    public String toString() {
        return "APIResponse [resultCode=" + resultCode + ", resultMsg="
                + resultMsg + "]";
    }

    public static <T> T parseWithJSON(String s, Class<T> classOfT) throws JsonParseException {
        Gson gson = new Gson();
        return (T) gson.fromJson(s, classOfT);
    }

}
