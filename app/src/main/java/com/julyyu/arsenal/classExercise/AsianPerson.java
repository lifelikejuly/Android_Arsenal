package com.julyyu.arsenal.classExercise;

import com.julyyu.utilslibrary.util.LogUtils;

/**
 * Created by julyyu on 2018/2/10.
 */

public class AsianPerson implements Person{
    @Override
    public void run() {
        LogUtils.printI("AsianPerson","run");
    }

    @Override
    public void speak() {
        LogUtils.printI("AsianPerson","speak");
    }
}
