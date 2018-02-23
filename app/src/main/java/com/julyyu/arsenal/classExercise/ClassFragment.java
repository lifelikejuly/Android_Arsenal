package com.julyyu.arsenal.classExercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.julyyu.arsenal.R;
import com.julyyu.uilibrary.fragment.BaseFragment;
import com.julyyu.utilslibrary.util.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by julyyu on 2018/2/10.
 */

public class ClassFragment extends BaseFragment{
    @Override
    protected int getLayout() {
        return R.layout.fragment_thread;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final AsianPerson asianPerson = new AsianPerson();
        Class             asian       = asianPerson.getClass();
        try {
            Method method = asian.getMethod("run",new Class[]{});
            method.invoke(asianPerson,new Object[]{});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //使用代理拦截person的接口
        Person asianPerson1 = (Person) Proxy.newProxyInstance(AsianPerson.class.getClassLoader(),AsianPerson.class.getInterfaces(),new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                LogUtils.printI("newProxyInstance","invoke");
                return method.invoke(asianPerson,args);
            }
        });
        asianPerson1.run();
    }
}
