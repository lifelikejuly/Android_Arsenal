package com.julyyu.arsenal.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.julyyu.arsenal.R;
import com.julyyu.uilibrary.fragment.BaseFragment;
import com.julyyu.utilslibrary.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by julyyu on 2018/1/27.
 */

public class HandlerFragment extends BaseFragment {

    CustomerThread customerThread;
    @BindView(R.id.btn_send)
    AppCompatButton btnSend;

    @Override
    protected int getLayout() {
        return R.layout.fragment_handler;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Handler handler = new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                return false;
//            }
//        });
//
//        handler.sendEmptyMessageAtTime(1,2000);
//        handler.sendEmptyMessageDelayed(0,3000);
//
//        HandlerThread handlerThread = new HandlerThread("handlerThread");

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LogUtils.printI("msg  get it too!");
            }
        };

        customerThread = new CustomerThread();
        customerThread.start();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = customerThread.handler.obtainMessage();
                message.what = 0;
                message.sendToTarget();
            }
        });

//        handler.
    }


    class CustomerThread extends Thread {
        Handler handler;
        Handler handler1;
        int i =1;
        @Override
        public void run() {
            super.run();
            if(Looper.myLooper() == null){
                Looper.prepare();
            }
//                Looper.prepare();
            //每个线程只能有一个Looper java.lang.RuntimeException: Only one Looper may be created per thread
            //message根据发送先后顺序在handleMessage依次接收
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if(i%2 == 0){
                        LogUtils.printI("msg  get it !" + i);
                    }else{
                        try {
                            Thread.sleep(2000);
                            LogUtils.printI("msg  get it !" + i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    i++;
                }
            };
//            handler1 = new Handler(){
//                @Override
//                public void handleMessage(Message msg) {
//                    super.handleMessage(msg);
//                }
//            };

            Looper.loop();
//            Looper.myLooper().quit();
            //Looper.quit() 不安全退出主要是将messages置为null 清空messages /安全退出根据message的时间判断置空list还是
            //线程实例Handler之前需要调用Looper.prepare()
            //java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
        }
    }

}
