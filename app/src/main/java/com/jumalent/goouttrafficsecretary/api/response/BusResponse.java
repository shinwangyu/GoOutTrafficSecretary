package com.jumalent.goouttrafficsecretary.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by seeroo_dev on 2016. 12. 8..
 */
public class BusResponse {
    //url : http://data.seoul.go.kr/openinf/openapiview.jsp?infId=OA-12764 여기 가보면 api 연동 샘플 나옴

    @SerializedName("subwayId")
    private String subwayId;        //지하철호선ID

    @SerializedName("updnLine")
    private String updnLine;        //상하행선 구분 (2호선 : (내선,외선),상행,하행)

    @SerializedName("trainLineNm")
    private String trainLineNm;     //도착지방면 (성수행 - 구로디지털단지방면)

    @SerializedName("subwayHeading")
    private String subwayHeading;   //내리는문방향 (오른쪽 왼쪽)

    @SerializedName("statnFid")
    private String statnFid;        //이전지하철역ID

    @SerializedName("statnTid")
    private String statnTid;        //다음지하철역ID

    @SerializedName("statnId")
    private String statnId;         //지하철역ID

    @SerializedName("statnNm")
    private String statnNm;         //지하철역명

    @SerializedName("trnsitCo")
    private String trnsitCo;        //환승노선수

    @SerializedName("ordkey")
    private String ordkey;      	//도착예정열차순번

    @SerializedName("subwayList")
    private String subwayList;	    //연계호선ID    (1002, 1007 등 연계대상 호상ID)

    @SerializedName("statnList")
    private String statnList;   	//연계지하철역ID  (1002000233,1007000744)

    @SerializedName("btrainSttus")
    private String btrainSttus; 	//열차종류  (급행,ITX)

    @SerializedName("barvlDt")
    private String barvlDt;     	//열차도착예정시간  (단위:초)

    @SerializedName("btrainNo")
    private String btrainNo;    	//열차번호  (현재운행하고 있는 호선별 열차번호)

    @SerializedName("bstatnId")
    private String bstatnId;	    //종착지하철역ID

    @SerializedName("bstatnNm")
    private String bstatnNm;	    //종착지하철역명

    @SerializedName("recptnDt")
    private String recptnDt;	    //열차도착정보를 생성한 시각

    @SerializedName("arvlMsg2")
    private String arvlMsg2;	    //첫번째도착메세지  (전역 진입, 전역 도착 등)

    @SerializedName("arvlMsg3")
    private String arvlMsg3;	    //두번째도착메세지  (종합운동장 도착, 12분 후 (광명사거리) 등)

    @SerializedName("arvlCd")
    private String arvlCd;	        //도착코드  (0:진입, 1:도착, 2:출발, 3:전역출발, 4:전역진입, 5:전역도착, 99:운행중)



    public String getSubwayId() {
        return subwayId;
    }

    public void setSubwayId(String subwayId) {
        this.subwayId = subwayId;
    }

    public String getUpdnLine() {
        return updnLine;
    }

    public void setUpdnLine(String updnLine) {
        this.updnLine = updnLine;
    }

    public String getTrainLineNm() {
        return trainLineNm;
    }

    public void setTrainLineNm(String trainLineNm) {
        this.trainLineNm = trainLineNm;
    }

    public String getSubwayHeading() {
        return subwayHeading;
    }

    public void setSubwayHeading(String subwayHeading) {
        this.subwayHeading = subwayHeading;
    }

    public String getStatnFid() {
        return statnFid;
    }

    public void setStatnFid(String statnFid) {
        this.statnFid = statnFid;
    }

    public String getStatnTid() {
        return statnTid;
    }

    public void setStatnTid(String statnTid) {
        this.statnTid = statnTid;
    }

    public String getStatnId() {
        return statnId;
    }

    public void setStatnId(String statnId) {
        this.statnId = statnId;
    }

    public String getStatnNm() {
        return statnNm;
    }

    public void setStatnNm(String statnNm) {
        this.statnNm = statnNm;
    }

    public String getTrnsitCo() {
        return trnsitCo;
    }

    public void setTrnsitCo(String trnsitCo) {
        this.trnsitCo = trnsitCo;
    }

    public String getOrdkey() {
        return ordkey;
    }

    public void setOrdkey(String ordkey) {
        this.ordkey = ordkey;
    }

    public String getSubwayList() {
        return subwayList;
    }

    public void setSubwayList(String subwayList) {
        this.subwayList = subwayList;
    }

    public String getStatnList() {
        return statnList;
    }

    public void setStatnList(String statnList) {
        this.statnList = statnList;
    }

    public String getBtrainSttus() {
        return btrainSttus;
    }

    public void setBtrainSttus(String btrainSttus) {
        this.btrainSttus = btrainSttus;
    }

    public String getBarvlDt() {
        return barvlDt;
    }

    public void setBarvlDt(String barvlDt) {
        this.barvlDt = barvlDt;
    }

    public String getBtrainNo() {
        return btrainNo;
    }

    public void setBtrainNo(String btrainNo) {
        this.btrainNo = btrainNo;
    }

    public String getBstatnId() {
        return bstatnId;
    }

    public void setBstatnId(String bstatnId) {
        this.bstatnId = bstatnId;
    }

    public String getBstatnNm() {
        return bstatnNm;
    }

    public void setBstatnNm(String bstatnNm) {
        this.bstatnNm = bstatnNm;
    }

    public String getRecptnDt() {
        return recptnDt;
    }

    public void setRecptnDt(String recptnDt) {
        this.recptnDt = recptnDt;
    }

    public String getArvlMsg2() {
        return arvlMsg2;
    }

    public void setArvlMsg2(String arvlMsg2) {
        this.arvlMsg2 = arvlMsg2;
    }

    public String getArvlMsg3() {
        return arvlMsg3;
    }

    public void setArvlMsg3(String arvlMsg3) {
        this.arvlMsg3 = arvlMsg3;
    }

    public String getArvlCd() {
        return arvlCd;
    }

    public void setArvlCd(String arvlCd) {
        this.arvlCd = arvlCd;
    }


}
