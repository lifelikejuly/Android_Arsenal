package com.julyyu.uilibrary.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by julyyu on 2017/6/14.
 *
 */

public abstract class BasePopupWindow extends PopupWindow implements View.OnClickListener{

    protected Context mContext;
    protected View    mRootView;
    public BasePopupWindow(Context context){
        mContext = context;
        mRootView = LayoutInflater.from(context).inflate(getLayout(),null);
        initView();
        setWindow();
        initData();
    }
    @Override
    public void onClick(View v) {
    }
    protected abstract void initView();
    protected abstract void initData();
    @LayoutRes
    protected abstract int getLayout();
    protected void setWindow() {
        this.setContentView(mRootView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x88000000);
        this.setBackgroundDrawable(dw);
    }
}
