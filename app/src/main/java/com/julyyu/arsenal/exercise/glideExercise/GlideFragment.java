package com.julyyu.arsenal.exercise.glideExercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.julyyu.arsenal.ui.base.BaseAppFragment;

/**
 * Created by julyyu on 2018/3/16.
 */

public class GlideFragment extends BaseAppFragment{
    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(getContext())
                .load("")
                .into(new ImageView(getContext()));
        Glide.with(getContext())
                .download(null)
                .into(new ImageView(getContext()));
    }
}
