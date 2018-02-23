package com.julyyu.arsenal.exercise.ipcExercise;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.julyyu.utilslibrary.util.LogUtils;

/**
 * Created by julyyu on 2018/2/13.
 */

public class MessengerService extends Service{


    private class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    LogUtils.printI(getClass().getName(),"服务端收到消息 receive msg from Client：" + msg.getData().getString("msg"));
//                    ToastService.startService(getApplication(),"服务端收到消息 receive msg from Client：" + msg.getData().getString("msg"));
                    Toast.makeText(getApplication(),"服务端收到消息 receive msg from Client：" + msg.getData().getString("msg"),Toast.LENGTH_SHORT).show();
                    //回复客户端消息
                    Messenger client = msg.replyTo;
                    Message relpyMessage = Message.obtain(null,0);
                    Bundle bundle = new Bundle();
                    bundle.putString("msg","Service Msg 服务端回值，我收到了谢谢");
                    relpyMessage.setData(bundle);
                    try{
                        client.send(relpyMessage);
                    }catch (RemoteException e){
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }

    }

    private final Messenger messenger = new Messenger(new MessengerHandler());


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

}
