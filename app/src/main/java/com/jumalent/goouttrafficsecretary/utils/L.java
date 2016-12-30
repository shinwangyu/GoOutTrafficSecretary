package com.jumalent.goouttrafficsecretary.utils;


public class L {
	
	public static final String DEFAULT_TAG = "L";
    
    private enum LOG_LEVEL {
    	unspecified,
    	I,
    	D,
    	E,
    	V,
    	W
    };
    
    public static boolean LOG_ON = false;
    public static String IDENTIFIER = "► ";
    
    public static void p(String tag, Object msg) {
    	if(msg == null)
    		msg = "";
    	
    	print(LOG_LEVEL.W, tag, IDENTIFIER + msg.toString());
    }
    
    public static void p(String tag, Object msg , Throwable e) {
    	if(msg == null)
    		msg = "";
    	print(LOG_LEVEL.W, tag, IDENTIFIER + msg.toString());
        e.printStackTrace();
    }
    
    /**
     * Log.d (Print out normal message)
     * @param msg
     */
    public static void d(Object msg) {
    	if(msg == null)
    		msg = "";
    	print(LOG_LEVEL.D, DEFAULT_TAG, IDENTIFIER + msg.toString());
    }

    public static void d(String tag, Object msg) {
        if(msg == null)
            msg = "";
        print(LOG_LEVEL.D, tag, IDENTIFIER + msg.toString());
    }
    
    /**
     * Log.e (Print out error message)
     * @param msg
     */
    public static void e(Object msg) {
    	if(msg == null)
    		msg = "";
    	print(LOG_LEVEL.E, DEFAULT_TAG, IDENTIFIER + msg.toString());
    }
    
    public static void e(String tag, Object msg) {
    	if(msg == null)
    		msg = "";
    	print(LOG_LEVEL.E, tag, IDENTIFIER + msg.toString());
    }
    
    public static void e(String tag, Object msg , Throwable e) {
    	if(msg == null)
    		msg = "";
    	print(LOG_LEVEL.E, tag, IDENTIFIER + msg.toString());
        e.printStackTrace();
    }
    
    /**
     * Log.i (Print out information message)
     * @param tag
     * @param msg
     */
    public static void i(String tag, Object msg){
    	if(msg == null)
    		msg = "";
    	print(LOG_LEVEL.I, tag, msg);
    }
    
    /**
     * Log.w (Print out warning message)
     * @param tag
     * @param msg
     */
    public static void w(String tag, Object msg){
    	if(msg == null)
    		msg = "";
    	print(LOG_LEVEL.W, tag, msg);
    }
    
    public static void print(LOG_LEVEL type, String tag, Object msg) {
        if(LOG_ON) 
            switch (type) {
                case I:
                	android.util.Log.i(tag, msg.toString());
                    break;
                case D:
                	android.util.Log.d(tag, msg.toString());
                    break;
                case E:
                	android.util.Log.e(tag, msg.toString());
                    break;
                case V:
                	android.util.Log.v(tag, msg.toString());
                    break;
                case W:
                	android.util.Log.w(tag, msg.toString());
                    break;
                case unspecified:
                default:
                		break;
            }        
    }


    public static void setDebug(boolean isdebug){
        LOG_ON = isdebug;
    }
}