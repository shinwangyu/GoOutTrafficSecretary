package wangdaeji.com.goouttrafficsecretary.api;

/**
 * Created by seeroo_dev on 2016. 12. 8..
 */
public class APIConstant
{
    private static final String TAG = "APIConstant";

    public static final String 	HOST = "swopenAPI.seoul.go.kr";	//
    public static final int 	PORT = 0;
    public static final String 	PATH = "/api/subway";
    public static final String  TYPE = "/json";

    protected String getDefaultScheme() { return "http://"; }
    protected String getDefaultHost() { return HOST; }
    protected int getDefaultPort() { return PORT; }
    protected String getDefaultPath() { return PATH; }
    protected String getDefaultType() {return TYPE; }


}
