package com.jumalent.goouttrafficsecretary.api.response;

public class ReqAppInfoResponse extends com.jumalent.goouttrafficsecretary.api.response.APIResponse {
	
	private String tempAppKeyID;
	private int isUpdate;
	private int isMandatory;
	private String marketUrl;
	private String randomKey;
	
	private String propertyValue;
	private String publicKey;
	private String certPubKey;
	private int isChangePWD;
	private int isSetupMainCard;
	private int sessionTimeout;

	private String anounSerno;
	private String anounTitlNm;
	private String pupAnounYn;
	private String anounCtnt;
	private String linkUrl;
	private String anounImgNm;
	
	// 20150129 앱카드몰 적용 url
	private String appcardMallUrl;
	
	// 20150616 frogda 홈쇼핑 간편결제
	private String encClnn;
	private String encCtn;
	private String encUserName;
	
	// 20151110 frogda 최신앱버전 
	private String lastAppVersion;


	public String getTempAppKeyID() {
		return tempAppKeyID;
	}
	public void setTempAppKeyID(String tempAppKeyID) {
		this.tempAppKeyID = tempAppKeyID;
	}
	public int getIsUpdate() {
		return isUpdate;
	}
	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}
	public int getIsMandatory() {
		return isMandatory;
	}
	public void setIsMandatory(int isMandatory) {
		this.isMandatory = isMandatory;
	}
	public String getMarketUrl() {
		return marketUrl;
	}
	public void setMarketUrl(String marketUrl) {
		this.marketUrl = marketUrl;
	}
	public String getRandomKey() {
		return randomKey;
	}
	public void setRandomKey(String randomKey) {
		this.randomKey = randomKey;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getCertPubKey() {
		return certPubKey;
	}
	public void setCertPubKey(String certPubKey) {
		this.certPubKey = certPubKey;
	}
	public int getIsChangePWD() {
		return isChangePWD;
	}
	public void setIsChangePWD(int isChangePWD) {
		this.isChangePWD = isChangePWD;
	}
	public int getIsSetupMainCard() {
		return isSetupMainCard;
	}
	public void setIsSetupMainCard(int isSetupMainCard) {
		this.isSetupMainCard = isSetupMainCard;
	}
	
	public int getSessionTimeout() {
		return sessionTimeout;
	}
	public void setSessionTimeout(int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
	public String getAnounSerno() {
		return anounSerno;
	}
	public void setAnounSerno(String anounSerno) {
		this.anounSerno = anounSerno;
	}
	public String getAnounTitlNm() {
		return anounTitlNm;
	}
	public void setAnounTitlNm(String anounTitlNm) {
		this.anounTitlNm = anounTitlNm;
	}
	public String getPupAnounYn() {
		return pupAnounYn;
	}
	public void setPupAnounYn(String pupAnounYn) {
		this.pupAnounYn = pupAnounYn;
	}
	public String getAnounCtnt() {
		return anounCtnt;
	}
	public void setAnounCtnt(String anounCtnt) {
		this.anounCtnt = anounCtnt;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getAnounImgNm() {
		return anounImgNm;
	}
	public void setAnounImgNm(String anounImgNm) {
		this.anounImgNm = anounImgNm;
	}
	public String getAppcardMallUrl() {
		return appcardMallUrl;
	}
	public void setAppcardMallUrl(String appcardMallUrl) {
		this.appcardMallUrl = appcardMallUrl;
	}
	public String getEncClnn() {
		return encClnn;
	}
	public void setEncClnn(String encClnn) {
		this.encClnn = encClnn;
	}
	public String getEncCtn() {
		return encCtn;
	}
	public void setEncCtn(String encCtn) {
		this.encCtn = encCtn;
	}
	public String getEncUserName() {
		return encUserName;
	}
	public void setEncUserName(String encUserName) {
		this.encUserName = encUserName;
	}
	public String getLastAppVersion() {
		return lastAppVersion;
	}
	public void setLastAppVersion(String lastAppVersion) {
		this.lastAppVersion = lastAppVersion;
	}
	@Override
	public String toString() {
		return "ReqAppInfoResponse [tempAppKeyID=" + tempAppKeyID
				+ ", isUpdate=" + isUpdate + ", isMandatory=" + isMandatory
				+ ", marketUrl=" + marketUrl + ", randomKey=" + randomKey
				+ ", propertyValue=" + propertyValue + ", publicKey="
				+ publicKey + ", certPubKey=" + certPubKey + ", isChangePWD="
				+ isChangePWD + ", isSetupMainCard=" + isSetupMainCard
				+ ", sessionTimeout=" + sessionTimeout + ", anounSerno="
				+ anounSerno + ", anounTitlNm=" + anounTitlNm + ", pupAnounYn="
				+ pupAnounYn + ", anounCtnt=" + anounCtnt + ", linkUrl="
				+ linkUrl + ", anounImgNm=" + anounImgNm + ", appcardMallUrl="
				+ appcardMallUrl + ", encClnn=" + encClnn + ", encCtn="
				+ encCtn + ", encUserName=" + encUserName + ", lastAppVersion="
				+ lastAppVersion + "]";
	}

}
