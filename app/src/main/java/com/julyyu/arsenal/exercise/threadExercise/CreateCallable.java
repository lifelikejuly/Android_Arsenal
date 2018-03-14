package com.julyyu.arsenal.exercise.threadExercise;

import com.julyyu.utilslibrary.util.LogUtils;

import java.util.concurrent.Callable;

/**
 * Created by julyyu on 2018/2/23.
 */

public class CreateCallable implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (; i < 100; i++) {
            LogUtils.printI(this.getClass().getName(),"num: " + i);
        }
        return i;
    }
}
