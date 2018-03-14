package com.julyyu.arsenal.exercise.objectCopyExercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.julyyu.arsenal.R;
import com.julyyu.arsenal.ui.base.BaseAppFragment;
import com.julyyu.utilslibrary.util.LogUtils;

/**
 * Created by julyyu on 2018/2/23.
 */

public class ObjectCopyFragment extends BaseAppFragment{

    private final String TAG = getClass().getName();

    @Override
    protected int getLayout() {
        return R.layout.view_null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtils.printI(TAG,"---------开始浅拷贝-----------");
        ShallowObject shallowObject = new ShallowObject("July",new Subject("Yu"));
        try {
            ShallowObject tempShallowObject = (ShallowObject) shallowObject.clone();
            LogUtils.printI(TAG,"原对象: " + shallowObject.getName() + "-" + shallowObject.getSubject().getName());
            LogUtils.printI(TAG,"拷贝对象: " + tempShallowObject.getName() + "-" + tempShallowObject.getSubject().getName());
            LogUtils.printI(TAG,"对象String类型成员是否一样: " + (shallowObject.getName() == tempShallowObject.getName()));
            LogUtils.printI(TAG,"对象自定义成员是否一样: " + (shallowObject.getSubject() == tempShallowObject.getSubject()));
            shallowObject.setName("Jack");
            shallowObject.getSubject().setName("Chen");
            LogUtils.printI(TAG,"原对象: " + shallowObject.getName() + "-" + shallowObject.getSubject().getName());
            LogUtils.printI(TAG,"拷贝对象: " + tempShallowObject.getName() + "-" + tempShallowObject.getSubject().getName());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        LogUtils.printI(TAG,"---------开始深拷贝-----------");
        DeepObject deepObject = new DeepObject("July",new Subject("Yu"));
        try {
            DeepObject tempDeepObject = (DeepObject) deepObject.clone();
            LogUtils.printI(TAG,"原对象: " + deepObject.getName() + "-" + deepObject.getSubject().getName());
            LogUtils.printI(TAG,"拷贝对象: " + tempDeepObject.getName() + "-" + tempDeepObject.getSubject().getName());
            LogUtils.printI(TAG,"对象String类型成员是否一样: " + (deepObject.getName() == tempDeepObject.getName()));
            LogUtils.printI(TAG,"对象自定义成员是否一样: " + (deepObject.getSubject() == tempDeepObject.getSubject()));
            deepObject.setName("Jack");
            deepObject.getSubject().setName("Chen");
            LogUtils.printI(TAG,"原对象: " + deepObject.getName() + "-" + deepObject.getSubject().getName());
            LogUtils.printI(TAG,"拷贝对象: " + tempDeepObject.getName() + "-" + tempDeepObject.getSubject().getName());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        LogUtils.printI(TAG,"---------开始赋值拷贝-----------");
        DeepObject shallow = new DeepObject("July",new Subject("Yu"));
        DeepObject shallowObject1 = shallow;
        LogUtils.printI(TAG,"原对象: " + shallow.getName() + "-" + shallow.getSubject().getName());
        LogUtils.printI(TAG,"拷贝对象: " + shallowObject1.getName() + "-" + shallowObject1.getSubject().getName());
        LogUtils.printI(TAG,"对象String类型成员是否一样: " + (shallow.getName() == shallowObject1.getName()));
        LogUtils.printI(TAG,"对象自定义成员是否一样: " + (shallow.getSubject() == shallowObject1.getSubject()));
        shallow.setName("Jack");
        shallow.getSubject().setName("Chen");
        LogUtils.printI(TAG,"原对象: " + shallow.getName() + "-" + shallow.getSubject().getName());
        LogUtils.printI(TAG,"拷贝对象: " + shallowObject1.getName() + "-" + shallowObject1.getSubject().getName());

    }
}
