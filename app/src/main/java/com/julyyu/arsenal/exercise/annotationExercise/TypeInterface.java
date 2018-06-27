package com.julyyu.arsenal.exercise.annotationExercise;

import android.animation.ValueAnimator;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by haocanyu on 09/04/2018.
 */



public class TypeInterface {

    static int repeatMode;

    @IntDef(flag = false, value = {1, 2})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
        //代替enum，据说枚举类极其耗费内存
    }


    public static void setInterfaceType(@RepeatMode int mode){
        repeatMode = mode;
    }
}
