package com.julyyu.arsenal.exercise.threadExercise;

import com.julyyu.utilslibrary.util.LogUtils;

/**
 * Created by julyyu on 2018/2/5.
 */

public class Thread2 extends Thread{

    @Override
    public void run() {
        super.run();
        int num  = CountManager.getInstance().getNum();;
        while (num < 10){
            try {
                num = CountManager.getInstance().getNum();
                CountManager.getInstance().addNum();
                LogUtils.printI(getClass().getName(), num + "");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
