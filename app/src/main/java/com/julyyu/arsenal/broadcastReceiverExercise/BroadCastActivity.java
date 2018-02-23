package com.julyyu.arsenal.broadcastReceiverExercise;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Network;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.julyyu.uilibrary.activity.BaseToolBarTitleActivity;
import com.julyyu.uilibrary.fragment.BaseFragment;
import com.julyyu.utilslibrary.util.LogUtils;

/**
 * Created by julyyu on 2018/2/12.
 */

public class BroadCastActivity extends BaseToolBarTitleActivity{

    BroadCasRec broadCasRec;
    LocalBroadCast localBroadCast;
    LocalBroadcastManager localBroadcastManager;

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDate() {
        broadCasRec = new BroadCasRec();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        registerReceiver(broadCasRec,intentFilter);
    }


    /**
     * 发送普通广播
     */
    public void sendNormalBroadCast(){
        Intent intent = new Intent();
        intent.setAction("xxx");
        sendBroadcast(intent);
    }

    public void systemBroadCast(){
        /**
         * 系统广播
         */
    }

    /**
     * 按照Priority属性值从大-小排序；
     * Priority属性相同者，动态注册的广播优先；
     */
    public void sendOrdedBroadCast(){
        Intent intent = new Intent();
        intent.setAction("xxx");
        sendOrderedBroadcast(intent,"");
    }

    public void createLocalBroadCast(){
        localBroadcastManager =  LocalBroadcastManager.getInstance(this);
        localBroadCast = new LocalBroadCast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        localBroadcastManager.registerReceiver(localBroadCast,intentFilter);
    }

    /**
     * 发送本地广播
     */
    public void sendLocalBroadCast(){
        if(localBroadcastManager != null){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_POWER_CONNECTED);
            localBroadcastManager.sendBroadcast(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(broadCasRec != null){
            unregisterReceiver(broadCasRec);
            broadCasRec = null;
        }
        if(localBroadcastManager != null && localBroadCast != null){
            localBroadcastManager.unregisterReceiver(localBroadCast);
            localBroadCast = null;
        }
    }


    class LocalBroadCast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtils.printI("localBroad");
        }
    }

}
