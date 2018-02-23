package com.julyyu.arsenal.exercise.noHttpExercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.julyyu.uilibrary.fragment.BaseFragment;
import com.yanzhenjie.nohttp.NoHttp;

/**
 * Created by julyyu on 2018/2/9.
 */

public class NoHttpUseFragment extends BaseFragment{

    public void Test(){

    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NoHttp.initialize(getContext());
    }
}
