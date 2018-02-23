package com.julyyu.arsenal.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.julyyu.arsenal.R;
import com.julyyu.arsenal.adapter.ItemAdapter;
import com.julyyu.arsenal.ui.view.refresh.NewSwipeRefreshLayout;
import com.julyyu.arsenal.ui.view.refresh.SwipeView;

import java.util.Arrays;

/**
 * Created by julyyu on 2017/7/30.
 */

public class refreshFragment extends Fragment implements NewSwipeRefreshLayout.OnRefreshListener{


//    @BindView(R.id.swiperefresh)
    protected SwipeView    swipeRefreshLayout;
//    @BindView(R.id.recycler)
    protected RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_newrefresh,null,false);
        swipeRefreshLayout = (SwipeView) view.findViewById(R.id.swiperefresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setProgressViewOffset(false,-600,0);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ItemAdapter(Arrays.asList(new String[]{"1","2","3","4","4","4","4","4","4","4","4","4","4","4","4","4","4","4","4","4","4","4","4"})));
    }

    @UiThread
    void finsiLoading(){
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        finsiLoading();
                    }
                });

            }
        }).start();
    }
}
