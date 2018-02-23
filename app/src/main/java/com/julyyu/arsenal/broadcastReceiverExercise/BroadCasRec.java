package com.julyyu.arsenal.broadcastReceiverExercise;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by julyyu on 2018/2/12.
 */

public class BroadCasRec extends BroadcastReceiver{



    @Override
    public void onReceive(Context context, Intent intent) {

        abortBroadcast();//拦截广播
    }
    /**
     * 广播两种注册方式：静态、动态
     *
     */



    public void forward(){ //有序广播继续传递
        int    code   = 0;
        String data   = "hello";
        Bundle bundle = null;
        setResult(code, data, bundle);
    }

    public void getForward(){
        getResultExtras(true);
    }
}
