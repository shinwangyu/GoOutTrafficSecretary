package wangdaeji.com.goouttrafficsecretary.api.request;

/**
 * Created by seeroo_dev on 2016. 12. 9..
 */
public class RetroRequest {
//    http://swopenAPI.seoul.go.kr/api/subway/(인증키)/xml/realtimeStationArrival/0/5/서울

    private String authkey;     //인증키
    private String type;        //요청파일타입
    private String service;     //서비스명
    private int start_index;    //요청시작위치
    private int end_index;      //요청종료위치
    private String statnNm;     //지하철역명
}
