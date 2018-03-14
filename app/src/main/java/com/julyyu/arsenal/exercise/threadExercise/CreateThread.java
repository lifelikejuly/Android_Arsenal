package com.julyyu.arsenal.exercise.threadExercise;

import com.julyyu.utilslibrary.util.LogUtils;

/**
 * Created by julyyu on 2018/2/23.
 */

public class CreateThread extends Thread{

    int i = 0;

    @Override
    public void run() {
        super.run();
        for (; i < 100; i++) {
            LogUtils.printI(getName(),"num: " + i);
        }
    }
}
