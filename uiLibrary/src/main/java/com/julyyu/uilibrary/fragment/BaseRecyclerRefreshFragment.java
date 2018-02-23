package com.julyyu.uilibrary.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.julyyu.uilibrary.R;
import com.julyyu.uilibrary.R2;
import com.julyyu.uilibrary.adapter.BaseRecyclerAdapter;

import butterknife.BindView;

/**
 * Created by JulyYu on 2017/2/28.
 */

public abstract class BaseRecyclerRefreshFragment<A extends BaseRecyclerAdapter> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    protected abstract void freshLoadDates();

    @Override
    public void onRefresh() {
        freshLoadDates();
    }

    public void setAdapter(A adapter) {
        this.adapter = adapter;
        recyclerView.setAdapter(this.adapter);
    }
}
