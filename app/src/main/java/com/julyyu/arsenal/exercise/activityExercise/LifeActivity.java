package com.julyyu.arsenal.exercise.activityExercise;

import android.os.Bundle;

import com.julyyu.uilibrary.activity.BaseToolBarTitleActivity;

/**
 * Created by julyyu on 2018/2/12.
 */

public class LifeActivity extends BaseToolBarTitleActivity{

    /**
     * start stop 关于是否可见 / resume pause关于是否在前台
     * @return
     */

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initView() {
//            getSupportFragmentManager().beginTransaction().add();
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //
    }

}
