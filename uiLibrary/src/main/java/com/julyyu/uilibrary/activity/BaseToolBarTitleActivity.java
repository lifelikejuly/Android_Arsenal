package com.julyyu.uilibrary.activity;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.julyyu.uilibrary.R;

import butterknife.ButterKnife;

/**
 * Created by julyyu on 2017/2/25 0025.
 */

public abstract class BaseToolBarTitleActivity extends BaseActivity implements View.OnClickListener {

    TextView  tvTitle;
    ImageView ivRight;
    ImageView ivLeft;
    TextView  tvRight;
    TextView  tvLeft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initTitle();
        initView();
        initDate();
    }

    private void initTitle() {
        View view = LayoutInflater.from(this).inflate(R.layout.view_title, null);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL;
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(view, layoutParams);
        Toolbar parent = (Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        ivRight = (ImageView) view.findViewById(R.id.iv_right);
        ivLeft = (ImageView) view.findViewById(R.id.iv_left);
        tvRight = (TextView) view.findViewById(R.id.tv_right);
        tvLeft = (TextView) view.findViewById(R.id.tv_left);
        ivLeft.setOnClickListener(this);
        ivRight.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
        ivLeft.setImageResource(R.drawable.ic_back);
    }

    public void setActionTitle(String text) {
        tvTitle.setText(text);
    }

    public void setRightRes(@DrawableRes int res) {
        ivRight.setImageResource(res);
    }

    public void setLeftRes(@DrawableRes int res) {
        ivLeft.setImageResource(res);
    }

    public void setRightText(@NonNull String text) {
        tvRight.setText(text);
    }

    @LayoutRes
    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initDate();

    @Override
    public void onClick(View v) {
        if (v == ivLeft || v == tvLeft) {
            onLeftOnClick();
        } else if (v == ivRight || v == tvRight) {
            onRightOnClick();
        }
    }

    public void onRightOnClick() {

    }

    public void onLeftOnClick() {
        this.onBackPressed();
    }


    public void setLeftIvVisibility(boolean bool) {
        ivLeft.setVisibility(bool ? View.VISIBLE : View.GONE);
    }

    public void setRightIvVisibility(boolean bool) {
        ivRight.setVisibility(bool ? View.VISIBLE : View.GONE);
    }

    public void setLeftTvVisibiltity(boolean bool) {
        tvLeft.setVisibility(bool ? View.VISIBLE : View.GONE);
    }

    public void setRightTvVisibiltity(boolean bool) {
        tvRight.setVisibility(bool ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
