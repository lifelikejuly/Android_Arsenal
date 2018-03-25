package com.julyyu.arsenal.exercise.deviceExercise;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.alibaba.fastjson.JSON;
import com.julyyu.arsenal.R;
import com.julyyu.arsenal.ui.base.BaseAppFragment;
import com.julyyu.utilslibrary.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by julyyu on 2018/3/22.
 */

public class DeviceFragment extends BaseAppFragment {


    @BindView(R.id.sw_light)
    Switch swLight;
    Camera m_Camera;
    @Override
    protected int getLayout() {
        return R.layout.fragment_device;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    try{
                        m_Camera = Camera.open();
                        Camera.Parameters mParameters;
                        mParameters = m_Camera.getParameters();
                        mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        m_Camera.setParameters(mParameters);
                        LogUtils.printI(getClass().getName(), JSON.toJSONString(mParameters.getSupportedFlashModes()));
                    } catch(Exception ex){}
                }else{
                    try{
                        Camera.Parameters mParameters;
                        mParameters = m_Camera.getParameters();
                        mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        m_Camera.setParameters(mParameters);
                        m_Camera.release();
                    } catch(Exception ex){}
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
            Camera.Parameters mParameters;
            mParameters = m_Camera.getParameters();
            mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            m_Camera.setParameters(mParameters);
            m_Camera.release();
        } catch(Exception ex){}
    }
}
