package com.julyyu.utilslibrary.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

/**
 * Created by JulyYu on 2016/8/27.
 * 权限请求
 */
public class PermissionUtils {


    public static boolean checkingPermissionRegister(Context app, String permission){
        return ContextCompat.checkSelfPermission(app, permission)
                == PermissionChecker.PERMISSION_GRANTED;
    }



    public static boolean checkingPermissionRegister(Context app,String... permissions){
        for(String permission : permissions){
            if(!checkingPermissionRegister(app,permission)){
                return false;
            }
        }
        return true;
    }

    public static void requestPermission(Activity activity,int requestCode,String... permissions){
        ActivityCompat.requestPermissions(activity,permissions,requestCode);
    }

    public static boolean hasRejectPermission(@Nullable int[] grantResults){
        for(int grantResult : grantResults){
            if(grantResult != PermissionChecker.PERMISSION_GRANTED){
                return true;
            }
        }
        return false;
    }
}
