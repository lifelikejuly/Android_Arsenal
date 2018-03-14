package com.julyyu.arsenal.exercise.threadExercise;

import com.julyyu.utilslibrary.util.LogUtils;

/**
 * Created by julyyu on 2018/2/23.
 */

public class CreateRunnable implements Runnable {

    int i = 0;

    @Override
    public void run() {
        for (; i < 100; i++) {
            LogUtils.printI(this.getClass().getName(),"num: " + i);
        }
    }
}
