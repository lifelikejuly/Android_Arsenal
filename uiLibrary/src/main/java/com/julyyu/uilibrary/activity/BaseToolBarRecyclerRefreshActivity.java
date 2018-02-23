package com.julyyu.uilibrary.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.julyyu.uilibrary.R;
import com.julyyu.uilibrary.R2;
import com.julyyu.uilibrary.adapter.BaseRecyclerAdapter;

import butterknife.BindView;

/**
 * Created by JulyYu on 2017/2/27.
 */

public abstract class BaseToolBarRecyclerRefreshActivity<A extends BaseRecyclerAdapter> extends BaseToolBarTitleActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R2.id.swiperefresh)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R2.id.recycler)
    protected RecyclerView recyclerView;
    protected A adapter;
    @Override
    public int getLayout() {
        return R.layout.view_refresh_recyclerview;
    }

    @Override
    public void initView() {
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    protected abstract void freshLoadDates();

    @Override
    public void onRefresh() {
        freshLoadDates();
    }

    public void setAdapter(A adapter){
        this.adapter = adapter;
        recyclerView.setAdapter(this.adapter);
    }
}
