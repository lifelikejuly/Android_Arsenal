package com.julyyu.arsenal.exercise.reflectionExercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.julyyu.arsenal.R;
import com.julyyu.uilibrary.fragment.BaseFragment;
import com.julyyu.utilslibrary.util.LogUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * Created by julyyu on 2018/2/10.
 */

public class ReflectionFragment extends BaseFragment{

    private  final String TAG = this.getClass().getName();

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

        Class<GenericTest> genericTestClass = GenericTest.class;
        Field[] fields = genericTestClass.getDeclaredFields();
        for(Field field : fields){
            String name = field.getName();
            LogUtils.printI(TAG,"Name = " + name);
            Class<?> cl = field.getType();
            LogUtils.printI(TAG,"Class = " + cl);
            Type type = field.getGenericType();
            LogUtils.printI(TAG,"Type = " + type);
            if(type instanceof ParameterizedType)
            {
                // 强制类型转换
                ParameterizedType pType = (ParameterizedType)type;
                // 获取原始类型
                Type rType = pType.getRawType();
                LogUtils.printI(TAG,"原始类型是：" + rType);
                // 取得泛型类型的泛型参数
                Type[] tArgs = pType.getActualTypeArguments();
                LogUtils.printI(TAG,"泛型类型是:");
                for (int i = 0; i < tArgs.length; i++)
                {
                    System.out.println("第" + i + "个泛型类型是：" + tArgs[i]);
                }
            }
        }
    }
}
