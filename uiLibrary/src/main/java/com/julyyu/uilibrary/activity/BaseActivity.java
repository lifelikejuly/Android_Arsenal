package com.julyyu.uilibrary.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by JulyYu on 2017/4/10.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{


    protected void fastStartActivity(Class<?> activity){
        startActivity(new Intent(this,activity));
    }

    @Override
    public void onClick(View v) {

    }

    protected void showToast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}
