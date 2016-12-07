package wangdaeji.com.goouttrafficsecretary;

import android.app.Application;
import android.content.Context;

/**
 * Created by seeroo_dev on 2016. 12. 7..
 */
public class TrafficApplication extends Application {

    private String mUserName;
    private String mUserPassword;
    private String mUserAction;

    private static TrafficApplication get(Context context) {
        return (TrafficApplication) context.getApplicationContext();
    }

    public static TrafficApplication create(Context context) {
        return TrafficApplication.get(context);
    }




}



