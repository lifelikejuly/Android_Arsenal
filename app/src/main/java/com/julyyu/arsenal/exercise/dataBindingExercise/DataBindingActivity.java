package com.julyyu.arsenal.exercise.dataBindingExercise;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.julyyu.arsenal.R;
import com.julyyu.arsenal.databinding.ActivityDatabindingBinding;
import com.julyyu.arsenal.ui.base.BaseAppFragment;
import com.julyyu.uilibrary.activity.BaseActivity;
import com.julyyu.uilibrary.activity.BaseToolBarTitleActivity;

/**
 * Created by julyyu on 2018/2/28.
 */

public class DataBindingActivity extends BaseActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabindingBinding activityDatabindingBinding = DataBindingUtil.setContentView(this,R.layout.activity_databinding);
        User                       user                       = new User("July",24);
        activityDatabindingBinding.setUser(user);
    }
}
