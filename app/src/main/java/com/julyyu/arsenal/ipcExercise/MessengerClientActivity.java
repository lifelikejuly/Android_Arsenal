package com.julyyu.arsenal.ipcExercise;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.julyyu.arsenal.R;
import com.julyyu.arsenal.ToastService;
import com.julyyu.uilibrary.activity.BaseToolBarTitleActivity;
import com.julyyu.utilslibrary.util.LogUtils;

/**
 * Created by julyyu on 2018/2/13.
 */

public class MessengerClientActivity extends BaseToolBarTitleActivity {

    private Messenger messenger;


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            messenger = new Messenger(service);
            Message message = Message.obtain(null, 0);
            Bundle  bundle  = new Bundle();
            bundle.putString("msg", "Client Msg 客户端消息");
            message.setData(bundle);
            message.replyTo = mGetReplyMessenger;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    LogUtils.printI(getClass().getName(), "客户端消息收到消息 receviced msg from MessegerService: " + msg.getData().getString("msg"));
//                    ToastService.startService(MessengerClientActivity.this,"客户端消息收到消息 receviced msg from MessegerService: " + msg.getData().getString("msg"));
                    showToast("客户端消息收到消息 receviced msg from MessegerService: " + msg.getData().getString("msg"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());


    @Override
    protected int getLayout() {
        return R.layout.view_null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDate() {
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
