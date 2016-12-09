package wangdaeji.com.goouttrafficsecretary.utils;

import android.util.Log;

/**
 * Created by seeroo_dev on 2016. 12. 7..
 * 로그 클래스 - 필요 시 추가해서 사용
 */
public class L {
    public static boolean DEBUG  = true;


    public static void e(String tag, String _str){
        if(DEBUG){
            Log.e(tag, _str);
        }
    }

    public static void e(String _str){
        if(DEBUG){
            Log.e("", _str);
        }
    }

    public static void d(String tag, String _str){
        if(DEBUG){
            Log.d(tag, _str);
        }
    }

    public static void d(String _str){
        if(DEBUG){
            Log.d("", _str);
        }
    }

}
