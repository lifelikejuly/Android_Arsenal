package com.julyyu.arsenal.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;


import com.julyyu.arsenal.R;
import com.julyyu.uilibrary.fragment.BaseFragment;
import com.julyyu.uilibrary.view.defaultPage.BadActionListener;
import com.julyyu.uilibrary.view.defaultPage.DefaultPageView;
import com.julyyu.uilibrary.view.defaultPage.ErrorActionListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by julyyu on 2017/7/25.
 */

public class DefaultPageFragment extends BaseFragment {

    @BindView(R.id.default_page)
    DefaultPageView defaultPageView;
    @Override
    protected int getLayout() {
        return R.layout.view_defaultpage;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        defaultPageView.setDefaultParameter(R.mipmap.ic_launcher,"DefaultPage","","");
        defaultPageView.setBadActionListener(new BadActionListener() {
            @Override
            public void ActionOnClick() {
                Toast.makeText(getContext(),"badAction",Toast.LENGTH_SHORT).show();
            }
        });
        defaultPageView.setErrorActionListener(new ErrorActionListener() {
            @Override
            public void ActionOnClick() {
                Toast.makeText(getContext(),"errorAction",Toast.LENGTH_SHORT).show();
            }
        });
    }
    int pageNum = 0;
    @OnClick(R.id.btn_change)
    public void changePage(View view){
        pageNum++;
        switch (pageNum % 3){
            case 0:
                defaultPageView.showDefaultPage();
                break;
            case 1:
                defaultPageView.showBadPage();
                break;
            case 2:
                defaultPageView.showErrorPage();
                break;
        }
    }

}
