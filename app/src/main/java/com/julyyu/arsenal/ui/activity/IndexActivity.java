package com.julyyu.arsenal.ui.activity;


import android.os.Bundle;
import android.os.PersistableBundle;

import com.julyyu.arsenal.Route;
import com.julyyu.arsenal.ui.adapter.ItemAdapter;
import com.julyyu.uilibrary.activity.BaseToolBarRecyclerRefreshActivity;
import com.julyyu.uilibrary.adapter.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JulyYu on 2017/4/14.
 */

public class IndexActivity extends BaseToolBarRecyclerRefreshActivity<ItemAdapter> {


    @Override
    public void initView() {
        super.initView();
        setActionTitle("Index");
        setLeftIvVisibility(false);
        swipeRefreshLayout.setEnabled(false);
        List<String> strings = new ArrayList<>();
        strings.add("默认页");
        strings.add("设备信息");
        strings.add("自定义下拉");
        strings.add("图片压缩");
        strings.add("聊天输入");
        strings.add("跨进程通信");
        strings.add("DataBinding使用");
        setAdapter(new ItemAdapter(strings));
        this.adapter.setItemSingleListener(new BaseRecyclerAdapter.ItemSingleListener<String>() {
            @Override
            public void onSingleClick(String clickedTask, int position) {
                Route.goPage(IndexActivity.this,position);
            }
        });
    }

    @Override
    protected void initDate() {
        int position = getIntent().getIntExtra("position",0);
        List<String> strings = new ArrayList<>();
        switch (position){
            case 0:
                strings.add("反射代理");
                strings.add("多线程");
                strings.add("序列化");
                strings.add("浅拷贝和深拷贝");
                break;
            case 1:
                strings.add("LaunchrMode活动");
                strings.add("Handler、Thread的使用");
                strings.add("Notification通知");
                strings.add("Toast提示框");
                strings.add("闪光灯使用");
                strings.add("蓝牙BlueTooth使用");
                break;
            case 2:

                break;
            case 3:

                break;
        }

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


