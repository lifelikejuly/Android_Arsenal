package com.julyyu.arsenal.exercise.noHttpExercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.julyyu.uilibrary.fragment.BaseFragment;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.AsyncRequestExecutor;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;
import com.yanzhenjie.nohttp.rest.StringRequest;

/**
 * Created by julyyu on 2018/2/9.
 */

public class NoHttpUseFragment extends BaseFragment{

    public void Test(){

    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NoHttp.initialize(getContext());
        StringRequest request = new StringRequest("http://api.nohttp.net");
        AsyncRequestExecutor.INSTANCE.execute(0, request, new SimpleResponseListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                // 请求成功。
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                // 请求失败。
            }
        });

    }
}
