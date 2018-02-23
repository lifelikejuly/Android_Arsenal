package com.julyyu.utilslibrary.util;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;

import java.util.List;

/**
 *
 * Created by julyyu on 2017/7/13.
 * 应用进程相关
 */

public class AndroidProcessUtils {

    /**
     * 判断应用是否在后台
     * @param context
     * @return
     */
    public static boolean isApplicationBroughtToBackground(Context context) {
        ActivityManager                             activityManager           = (ActivityManager) context.getSystemService(Service.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        if (runningAppProcessInfoList==null){
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo processInfo : runningAppProcessInfoList) {
            if (processInfo.processName.equals(context.getPackageName()) &&
                    processInfo.importance==ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND){
                return true;
            }
        }
        return false;
    }
}
