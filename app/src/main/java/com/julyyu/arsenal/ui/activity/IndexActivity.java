package com.julyyu.arsenal.ui.activity;


import android.os.Bundle;
import android.os.PersistableBundle;

import com.julyyu.arsenal.Route;
import com.julyyu.arsenal.exercise.AccountManagerExercise.AccountManagerFragmengt;
import com.julyyu.arsenal.exercise.bluetoothExercise.BlueToothDeviceSearchFragment;
import com.julyyu.arsenal.exercise.deviceExercise.DeviceFragment;
import com.julyyu.arsenal.exercise.launchModeExercise.LaunchrModeFragment;
import com.julyyu.arsenal.exercise.notificationExercise.NoticationFragment;
import com.julyyu.arsenal.exercise.objectCopyExercise.ObjectCopyFragment;
import com.julyyu.arsenal.exercise.procSysytemExercise.ProcFragment;
import com.julyyu.arsenal.exercise.reflectionExercise.ReflectionFragment;
import com.julyyu.arsenal.exercise.serializationExercise.SerializationTestFragment;
import com.julyyu.arsenal.exercise.threadExercise.ThreadFragment;
import com.julyyu.arsenal.exercise.toastCustomExercise.ToastCustomFragment;
import com.julyyu.arsenal.exercise.ui.TextViewFragment;
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
    }

    @Override
    protected void initDate() {
        int position = getIntent().getIntExtra("position", 0);
        List<PageItem> strings = new ArrayList<>();
        switch (position) {
            case 0:
                strings.add(new PageItem("反射代理", ReflectionFragment.class));
                strings.add(new PageItem("多线程", ThreadFragment.class));
                strings.add(new PageItem("序列化", SerializationTestFragment.class));
                strings.add(new PageItem("浅拷贝和深拷贝", ObjectCopyFragment.class));
                break;
            case 1:
                strings.add( new PageItem("LaunchrMode活动", LaunchrModeFragment.class));
                strings.add(new PageItem("Handler、Thread的使用" , ReflectionFragment.class));
                strings.add(new PageItem("Notification通知", NoticationFragment.class));
                strings.add( new PageItem("Toast提示框", ToastCustomFragment.class));
                strings.add(new PageItem("闪光灯使用" , DeviceFragment.class));
                strings.add( new PageItem("蓝牙BlueTooth使用", BlueToothDeviceSearchFragment.class));
                strings.add( new PageItem("同步账号", AccountManagerFragmengt.class));
                strings.add( new PageItem("Proc文件系统", ProcFragment.class));
                break;
            case 2:
                strings.add( new PageItem("TextView跑马灯效果", TextViewFragment.class));
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


