package com.julyyu.uilibrary.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by julyyu on 2017/6/1.
 */

public abstract class BaseViewActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewBefore();
        setContentView(getLayout());
        ButterKnife.bind(this);
        initView();
        initDate();
    }

    @LayoutRes
    protected abstract int getLayout();

    @MenuRes
    protected abstract int getOptionsMenuLayout();

    protected void setContentViewBefore(){

    }
    protected void initView() {
    }

    protected void initDate() {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuLayout = getOptionsMenuLayout();
        if (menuLayout == 0)
            return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(menuLayout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showToast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}
