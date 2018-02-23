package com.julyyu.arsenal.exercise.toastCustomExercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.julyyu.arsenal.R;
import com.julyyu.arsenal.ui.base.BaseAppFragment;

import butterknife.BindView;

/**
 * Created by julyyu on 2018/1/23.
 */

public class ToastCustomFragment extends BaseAppFragment {
    @BindView(R.id.btn_toast)
    AppCompatButton btnToast;
    @BindView(R.id.btn_toast_custom)
    AppCompatButton btnToastCustom;

    @Override
    protected int getLayout() {
        return R.layout.fragment_toast;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        final View     layout   = inflater.inflate(R.layout.view_custom_toast, null);
        btnToastCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast.Builder builder = new CustomToast.Builder();
                builder.duratione(Toast.LENGTH_SHORT)
                        .gravity(Gravity.CENTER)
                        .text("sssss")
                        .layout(layout)
                        .build().show(getContext());
            }
        });
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"toast",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
