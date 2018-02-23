package com.julyyu.utilslibrary.util;

import android.util.Log;

/**
 * Created by JulyYu on 2016/12/28.
 * 日志工具
 */

public class LogUtils {

    public static final int A_MODE = 0x1F;
    public static final int I_MODE = 0x01;
    public static final int D_MODE = 0x02;
    public static final int V_MODE = 0x04;
    public static final int W_MODE = 0x08;
    public static final int E_MODE = 0x10;

    private static boolean isOpen = true;
    private static int     iMode  = A_MODE;
    private static String  TAG    = "LogUtil";

    
    public static void open(){
        isOpen = true;
    }
    public static void close(){
        isOpen = false;
    }
    
    public static void setMode(int mode){
        iMode = mode;
    }

    public static void setTag(String tag){
        TAG = tag;
    }
    
    public static void printI(String text){
        if((iMode & I_MODE) != I_MODE) return;
        if(isOpen) Log.i(TAG,text);
    }
    public static void printD(String text){
        if((iMode & D_MODE) != D_MODE) return;
        if(isOpen) Log.d(TAG,text);
    }
    public static void printV(String text){
        if((iMode & V_MODE) != V_MODE) return;
        if(isOpen) Log.v(TAG,text);
    }
    public static void printW(String text){
        if((iMode & W_MODE) != W_MODE) return;
        if(isOpen) Log.w(TAG,text);
    }
    public static void printE(String text){
        if((iMode & E_MODE) != E_MODE) return;
        if(isOpen) Log.e(TAG,text);
    }

    public static void printI(String tag,String text){
        if((iMode & I_MODE) != I_MODE) return;
        if(isOpen) Log.i(tag,text);
    }
    public static void printD(String tag,String text){
        if((iMode & D_MODE) != D_MODE) return;
        if(isOpen) Log.d(tag,text);
    }
    public static void printV(String tag,String text){
        if((iMode & V_MODE) != V_MODE) return;
        if(isOpen) Log.v(tag,text);
    }
    public static void printW(String tag,String text){
        if((iMode & W_MODE) != W_MODE) return;
        if(isOpen) Log.w(tag,text);
    }
    public static void printE(String tag,String text){
        if((iMode & E_MODE) != E_MODE) return;
        if(isOpen) Log.e(tag,text);
    }
    
}
