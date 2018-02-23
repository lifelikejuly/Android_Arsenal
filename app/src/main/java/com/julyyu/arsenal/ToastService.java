package com.julyyu.arsenal;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by julyyu on 2018/2/23.
 */

public class ToastService extends IntentService{

    public ToastService(String name) {
        super(name);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String text = intent.getStringExtra("msg");
        if(TextUtils.isEmpty(text)){
            Toast.makeText(getBaseContext(),text,Toast.LENGTH_SHORT).show();
        }
    }

    public static void startService(Context context,String text){
        Intent intent = new Intent(context,ToastService.class);
        intent.setAction("toast_service");
        intent.putExtra("msg",text);
        context.startService(intent);
    }
}
