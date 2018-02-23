package com.julyyu.arsenal.launchModeExercise;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.julyyu.arsenal.R;
import com.julyyu.arsenal.Route;
import com.julyyu.uilibrary.activity.BaseToolBarTitleActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by julyyu on 2017/12/29.
 */

public class BaseModeActivity extends BaseToolBarTitleActivity{

    @BindView(R.id.tv_launchr_mode)
    protected TextView tvLaunchrMode;


    @Override
    protected int getLayout() {
        return R.layout.activity_launchr;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDate() {

    }

    @OnClick(R.id.btn_standard)
    public void standard(){
        Route.goStandard(this);
    }
    @OnClick(R.id.btn_singleTop)
    public void singleTop(View view){
        Route.goSingleTop(this);
    }
    @OnClick(R.id.btn_singleTask)
    public void singleTask(View view){
        Route.goSingleTask(this);
    }
    @OnClick(R.id.btn_singleInstance)
    public void singleInstance(View view){
        Route.goSingleInstance(this);
    }
    @OnClick(R.id.btn_flag_new_task)
    public void flag_new_task(View view){
        Intent intent = new Intent(this,StandardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @OnClick(R.id.btn_flag_single_top)
    public void flag_single_top(View view){
        Intent intent = new Intent(this,StandardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
    @OnClick(R.id.btn_flag_clear_top)
    public void flag_clear_top(View view){
        Intent intent = new Intent(this,StandardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    @OnClick(R.id.btn_flag_clear_top_new_task)
    public void flag_clear_top_new_task(View view){
        Intent intent = new Intent(this,StandardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
