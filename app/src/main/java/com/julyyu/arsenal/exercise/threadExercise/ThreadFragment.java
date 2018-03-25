package com.julyyu.arsenal.exercise.threadExercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.julyyu.arsenal.R;
import com.julyyu.arsenal.ui.adapter.ItemAdapter;
import com.julyyu.uilibrary.adapter.BaseRecyclerAdapter;
import com.julyyu.uilibrary.fragment.BaseFragment;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by julyyu on 2018/2/12.
 */

public class ThreadFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected int getLayout() {
        return R.layout.view_recyclerview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ExecutorService executorService = ThreadFactory.
//        Executors.newFixedThreadPool();
//        Executors.newCachedThreadPool();
//        Executors.newScheduledThreadPool();
//        Executors.newSingleThreadExecutor();
//        ThreadPoolExecutor
        /**
         * corePoolSize 核心线程数
         * maximumPoolSize 最大线程数
         * keepAliveTime 非核心线程限制超时时长
         * unit keepAlveTime参数时间单位
         * workQueue 任务队列
         * threadFactory 线程工厂
         */
        recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        ItemAdapter itemAdapter = new ItemAdapter(Arrays.asList(new String[]{
                "线程池",

        }));
        itemAdapter.setItemSingleListener(new BaseRecyclerAdapter.ItemSingleListener<String>() {
            @Override
            public void onSingleClick(String clickedTask, int position) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(getContext(),ThreadPoolExecutorActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        recycler.setAdapter(itemAdapter);
    }

}
