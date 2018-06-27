package com.julyyu.arsenal.exercise.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.julyyu.arsenal.R;
import com.julyyu.arsenal.ui.base.BaseAppFragment;

import butterknife.BindView;

/**
 * Created by haocanyu on 18/04/2018.
 */

public class TextViewFragment extends BaseAppFragment{

    @BindView(R.id.tv_text)
    TextView mTvText;
    @Override
    protected int getLayout() {
        return R.layout.fragment_textview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvText.setSingleLine();
        mTvText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        mTvText.setMarqueeRepeatLimit(-1);
        mTvText.setText("一个标题太长会显示不下，所以想试试跑马灯是否可行。应该没问题可以跑起来的啦");
        mTvText.setSelected(true);
    }
}
