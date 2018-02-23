package com.julyyu.arsenal;

import android.app.Application;
import android.content.Context;

import com.julyyu.utilslibrary.util.LogUtils;


/**
 * Created by JulyYu on 2016/12/28.
 */

public class App extends Application {
    static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext =getApplicationContext();
        LogUtils.open();
//        CrashCaptureMachine.Builder builder = new CrashCaptureMachine.Builder();
//        builder.isLaunchrActivity(true)
//                .isRecordFile(false)
//                .mCrashLogFilePath(FileUtil.getEnvironmentPath())
//                .mCrashLogPrefixName("titit");
//        CrashCaptureMachine.getInstance(this).setConfiguration(builder);
    }

    public static Context getmContext() {
        return mContext;
    }
}
