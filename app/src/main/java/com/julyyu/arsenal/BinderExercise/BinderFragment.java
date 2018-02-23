package com.julyyu.arsenal.BinderExercise;

import android.os.Binder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.julyyu.uilibrary.fragment.BaseFragment;

/**
 * Created by julyyu on 2018/2/13.
 */

public class BinderFragment extends BaseFragment{
    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Binder binder = new Binder();
    }
}
