package com.julyyu.arsenal.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.julyyu.arsenal.R;
import com.julyyu.arsenal.ui.view.refresh.NewTextView;
import com.julyyu.arsenal.ui.view.refresh.ViewGropTest;
import com.julyyu.uilibrary.fragment.BaseFragment;

import butterknife.BindView;

/**
 * Created by julyyu on 2017/8/2.
 */

public class ViewGroupFragment extends BaseFragment {
    @BindView(R.id.viewgroup)
    ViewGropTest viewgroup;
    @Override
    protected int getLayout() {
        return R.layout.view_test_viewgroup;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NewTextView textView = new NewTextView(getContext());
        viewgroup.addView(textView);
    }

}
