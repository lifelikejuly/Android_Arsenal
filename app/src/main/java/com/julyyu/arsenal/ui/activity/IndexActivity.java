package com.julyyu.arsenal.ui.activity;


import android.os.Bundle;
import android.os.PersistableBundle;

import com.julyyu.arsenal.Route;
import com.julyyu.arsenal.ui.adapter.ItemAdapter;
import com.julyyu.arsenal.ui.adapter.PageItemAdapter;
import com.julyyu.arsenal.ui.model.PageItem;
import com.julyyu.uilibrary.activity.BaseToolBarRecyclerRefreshActivity;
import com.julyyu.uilibrary.adapter.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JulyYu on 2017/4/14.
 */

public class IndexActivity extends BaseToolBarRecyclerRefreshActivity<PageItemAdapter> {


    @Override
    public void initView() {
        super.initView();
        setActionTitle("Index");
        setLeftIvVisibility(false);
        swipeRefreshLayout.setEnabled(false);
//        List<String> strings = new ArrayList<>();
//        strings.add("默认页");
//        strings.add("设备信息");
//        strings.add("自定义下拉");
//        strings.add("图片压缩");
//        strings.add("聊天输入");
//        strings.add("跨进程通信");
//        strings.add("DataBinding使用");
//        setAdapter(new ItemAdapter(strings));
//        this.adapter.setItemSingleListener(new BaseRecyclerAdapter.ItemSingleListener<String>() {
//            @Override
//            public void onSingleClick(String clickedTask, int position) {
//                Route.goPage(IndexActivity.this,position);
//            }
//        });
    }

    @Override
    protected void initDate() {
        int position = getIntent().getIntExtra("position", 0);
        List<PageItem> strings = new ArrayList<>();
        switch (position) {
            case 0:
                strings.add(new PageItem("反射代理", "com.julyyu.arsenal.exercise.reflectionExercise.ReflectionFragment"));
                strings.add(new PageItem("多线程", "com.julyyu.arsenal.exercise.threadExercise.ThreadFragment"));
                strings.add(new PageItem("序列化", "com.julyyu.arsenal.exercise.serializationExercise.SerializationTestFragment"));
                strings.add(new PageItem("浅拷贝和深拷贝", "com.julyyu.arsenal.exercise.objectCopyExercise.ObjectCopyFragment"));
                break;
            case 1:
                strings.add( new PageItem("LaunchrMode活动", "com.julyyu.arsenal.exercise.launchModeExercise.LaunchrModeFragment"));
                strings.add(new PageItem("Handler、Thread的使用" , "com.julyyu.arsenal.exercise.reflectionExercise.ReflectionFragment"));
                strings.add(new PageItem("Notification通知", "com.julyyu.arsenal.exercise.notificationExercise.NoticationFragment"));
                strings.add( new PageItem("Toast提示框", "com.julyyu.arsenal.exercise.toastCustomExercise.ToastCustomFragment"));
                strings.add(new PageItem("闪光灯使用" , "com.julyyu.arsenal.exercise.deviceExercise.DeviceFragment"));
                strings.add( new PageItem("蓝牙BlueTooth使用", "com.julyyu.arsenal.exercise.bluetoothExercise.BlueToothDeviceSearchFragment"));
                break;
            case 2:

                break;
            case 3:

                break;
        }
        setAdapter(new PageItemAdapter(strings));
        this.adapter.setItemSingleListener(new BaseRecyclerAdapter.ItemSingleListener<PageItem>() {
            @Override
            public void onSingleClick(PageItem clickedTask, int position) {
                Route.goPageItem(IndexActivity.this, clickedTask);
            }
        });
    }

    @Override
    protected void freshLoadDates() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}


