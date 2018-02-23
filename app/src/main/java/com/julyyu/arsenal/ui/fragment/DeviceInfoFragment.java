package com.julyyu.arsenal.ui.fragment;

import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;


import com.julyyu.arsenal.R;
import com.julyyu.arsenal.ui.base.BaseAppFragment;
import com.julyyu.utilslibrary.util.DeviceInfoUtils;
import com.julyyu.utilslibrary.util.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by julyyu on 2017/7/6.
 */

public class DeviceInfoFragment extends BaseAppFragment {

    @BindView(R.id.tv_device_info)
    TextView tvDeviceInfo;
    @BindView(R.id.btn_getinfo)
    AppCompatButton btnGetinfo;

    @Override
    protected int getLayout() {
        return R.layout.view_deviceinfo;
    }

    @OnClick(R.id.btn_getinfo)
    void getInfo(View view){
        String[]      infos   = DeviceInfoUtils.getPhoneInfoStrList(getContext());
        StringBuilder builder = new StringBuilder();
        for (String info : infos) {
            builder.append(info + "\n");
        }
        builder.append(DeviceInfoUtils.getMac());
        tvDeviceInfo.setText(builder.toString());
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnLongClick(R.id.btn_getinfo)
    public boolean onclickView(View view){
        IntentUtils.shareTextInfo(getContext(),"设备信息",tvDeviceInfo.getText().toString());
        return true;
    }

}
