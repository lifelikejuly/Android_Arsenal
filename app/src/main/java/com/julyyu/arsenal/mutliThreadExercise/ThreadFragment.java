package com.julyyu.arsenal.mutliThreadExercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.julyyu.arsenal.R;
import com.julyyu.uilibrary.fragment.BaseFragment;

/**
 * Created by julyyu on 2018/2/5.
 */

public class ThreadFragment extends BaseFragment{

    Thread1 thread1;
    Thread2 thread2;
//    Handler handler;
    @Override
    protected int getLayout() {
        return R.layout.fragment_thread;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        thread1 = new Thread1();
        thread2 = new Thread2();
        thread1.start();
        thread2.start();
//        new Thread(thread1,"ss");
//        handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                thread1.run();
//                thread2.run();
//                handler.sendEmptyMessageDelayed(0,1000);
//            }
//        };
//        handler.sendEmptyMessageDelayed(0,1000);
    }


    @Override
    public void onStop() {
        super.onStop();
        try {
            thread1.wait();
            thread2.wait();
        } catch (IllegalMonitorStateException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            thread1 = null;
            thread2 = null;
        }

//        handler.getLooper().quit();
    }
}
