package com.julyyu.arsenal.ui.activity;

import com.julyyu.arsenal.Route;
import com.julyyu.arsenal.ui.adapter.ItemAdapter;
import com.julyyu.uilibrary.activity.BaseToolBarRecyclerRefreshActivity;
import com.julyyu.uilibrary.adapter.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julyyu on 2018/3/22.
 */

public class MainActivity extends BaseToolBarRecyclerRefreshActivity<ItemAdapter> {
    @Override
    protected void freshLoadDates() {

    }

    @Override
    protected void initDate() {

    }

    @Override
    public void initView() {
        super.initView();
        setActionTitle("Index");
        setLeftIvVisibility(false);
        swipeRefreshLayout.setEnabled(false);
        List<String> strings = new ArrayList<>();
        strings.add("Java基础");
        strings.add("基本组件");
        strings.add("UI相关");
        strings.add("第三方开源库");
        setAdapter(new ItemAdapter(strings));
        this.adapter.setItemSingleListener(new BaseRecyclerAdapter.ItemSingleListener<String>() {
            @Override
            public void onSingleClick(String clickedTask, int position) {
                Route.goIndex(MainActivity.this,position);
            }
        });
    }
}
