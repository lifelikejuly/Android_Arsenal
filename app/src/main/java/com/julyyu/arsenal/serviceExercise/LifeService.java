package com.julyyu.arsenal.serviceExercise;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by julyyu on 2018/2/12.
 */

public class LifeService extends Service{

    /**
     * 第一次 startService 会触发 onCreate 和 onStartCommand
     * 以后在服务运行过程中，每次 startService 都只会触发 onStartCommand
     */


    /**
     * bind注册绑定的Service的生命周期依附于注册者，当绑定的对象生命周期结束，该Service也就销毁
     * 第一次 bindService 会触发 onCreate 和 onBind
     * 以后在服务运行过程中，每次 bindService 都不会触发任何回调
     * @param intent
     * @return
     */

    /**
     * stopForeground() 可以取消通知，即将前台服务降为后台服务。
     * @param intent
     * @return
     */

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
