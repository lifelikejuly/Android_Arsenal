package com.julyyu.arsenal.exercise.launchModeExercise;

import android.view.View;

import com.julyyu.arsenal.R;
import com.julyyu.arsenal.Route;
import com.julyyu.uilibrary.fragment.BaseFragment;

import butterknife.OnClick;

/**
 * Created by julyyu on 2017/12/29.
 */

public class LaunchrModeFragment extends BaseFragment{

    @Override
    protected int getLayout() {
        return R.layout.view_launchrmode;
    }
    @OnClick(R.id.btn_standard)
    public void standard(){
        Route.goStandard(getContext());
    }
    @OnClick(R.id.btn_singleTop)
    public void singleTop(View view){
        Route.goSingleTop(getContext());
    }
    @OnClick(R.id.btn_singleTask)
    public void singleTask(View view){
        Route.goSingleTask(getContext());
    }
    @OnClick(R.id.btn_singleInstance)
    public void singleInstance(View view){
        Route.goSingleInstance(getContext());
    }
}
