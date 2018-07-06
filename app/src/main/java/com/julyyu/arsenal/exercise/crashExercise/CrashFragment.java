package com.julyyu.arsenal.exercise.crashExercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.julyyu.arsenal.R;
import com.julyyu.uilibrary.fragment.BaseFragment;

/**
 * Created by haocanyu on 2018/6/28.
 */

public class CrashFragment extends BaseFragment{
    @Override
    protected int getLayout() {
        return R.layout.fragment_crash;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnCrash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new NullPointerException();
            }
        });
    }


}
