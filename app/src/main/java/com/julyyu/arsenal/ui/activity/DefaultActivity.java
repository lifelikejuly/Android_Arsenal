package com.julyyu.arsenal.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;

import com.julyyu.arsenal.exercise.bluetoothExercise.BlueToothDeviceSearchFragment;
import com.julyyu.arsenal.exercise.deviceExercise.DeviceFragment;
import com.julyyu.arsenal.exercise.objectCopyExercise.ObjectCopyFragment;
import com.julyyu.arsenal.exercise.dataBindingExercise.DataBindingFragment;
import com.julyyu.arsenal.exercise.ipcExercise.IPCFragment;
import com.julyyu.arsenal.R;
import com.julyyu.arsenal.exercise.reflectionExercise.ReflectionFragment;
import com.julyyu.arsenal.exercise.threadExercise.ThreadFragment;
import com.julyyu.arsenal.exercise.toastCustomExercise.ToastCustomFragment;
import com.julyyu.arsenal.exercise.serializationExercise.SerializationTestFragment;
import com.julyyu.arsenal.ui.fragment.ChatInputFragment;
import com.julyyu.arsenal.ui.fragment.DefaultPageFragment;
import com.julyyu.arsenal.ui.fragment.DeviceInfoFragment;
import com.julyyu.arsenal.ui.fragment.HandlerFragment;
import com.julyyu.arsenal.ui.fragment.ImageCompressFragment;
import com.julyyu.arsenal.exercise.launchModeExercise.LaunchrModeFragment;
//import com.julyyu.arsenal.ui.fragment.WebViewFragment;
import com.julyyu.arsenal.exercise.notificationExercise.NoticationFragment;
import com.julyyu.arsenal.ui.fragment.refreshFragment;
import com.julyyu.arsenal.ui.model.PageItem;
import com.julyyu.uilibrary.activity.BaseToolBarTitleActivity;


/**
 * Created by julyyu on 2017/7/6.
 */

public class DefaultActivity extends BaseToolBarTitleActivity {

    Fragment fragment;

    @Override
    protected int getLayout() {
        return R.layout.activity_default;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDate() {
        PageItem pageItem = (PageItem) getIntent().getSerializableExtra("page");
        try {
            fragment = (Fragment) Class.forName(pageItem.pageName).newInstance();
            setFragmentPage(fragment);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        int position = getIntent().getIntExtra("position",0);
//        switch (position){
//            case 0:
//                setFragmentPage(new DefaultPageFragment());
//                break;
//            case 1:
//                setFragmentPage(new DeviceInfoFragment());
//                break;
//            case 2:
//                setFragmentPage(new refreshFragment());
//                break;
//            case 3:
//                setFragmentPage(new ImageCompressFragment());
//                break;
//            case 4:
//                setFragmentPage(new ChatInputFragment());
//                break;
//            case 5:
//                setFragmentPage(new LaunchrModeFragment());
//                break;
//            case 6:
//                setFragmentPage(new NoticationFragment());
//                break;
//            case 7:
//                setFragmentPage(new ToastCustomFragment());
//                break;
//            case 8:
//                setFragmentPage(new HandlerFragment());
//                break;
//            case 9:
//                setFragmentPage(new SerializationTestFragment());
//                break;
//            case 10:
//                setFragmentPage(new ThreadFragment());
//                break;
//            case 11:
//                setFragmentPage(new ReflectionFragment());
//                break;
//            case 12:
//                setFragmentPage(new IPCFragment());
//                break;
//            case 13:
//                setFragmentPage(new ObjectCopyFragment());
//                break;
//            case 14:
//                setFragmentPage(new DataBindingFragment());
//                break;
//            case 15:
//                setFragmentPage(new BlueToothDeviceSearchFragment());
//                break;
//            case 16:
//                setFragmentPage(new DeviceFragment());
//                break;
//        }
    }

    private void setFragmentPage(Fragment fragment){
        this.fragment = fragment;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout,fragment)
                .show(fragment)
                .commit();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragment.onActivityResult(requestCode,resultCode,data);
    }
}
