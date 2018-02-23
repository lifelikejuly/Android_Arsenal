package com.julyyu.arsenal.exercise.threadExercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.julyyu.uilibrary.fragment.BaseFragment;

/**
 * Created by julyyu on 2018/2/12.
 */

public class ThreadFragment extends BaseFragment{
    @Override
    protected int getLayout() {
        return 0;
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
    }
}
