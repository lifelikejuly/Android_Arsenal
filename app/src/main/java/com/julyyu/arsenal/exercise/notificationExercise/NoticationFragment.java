package com.julyyu.arsenal.exercise.notificationExercise;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.julyyu.arsenal.R;
import com.julyyu.arsenal.ui.base.BaseAppFragment;

import butterknife.BindView;

/**
 * Created by julyyu on 2018/1/23.
 */

public class NoticationFragment extends BaseAppFragment {

    @BindView(R.id.btn_normalNotic)
    AppCompatButton btnNormalNotic;
    @BindView(R.id.btn_Text)
    AppCompatButton btnText;

    @Override
    protected int getLayout() {
        return R.layout.fragment_notication;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnNormalNotic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSimple();
            }
        });

    }

    private void sendSimple(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(),"")
                .setSmallIcon(R.mipmap.ic_notification)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_notification))
                .setContentTitle("My notification")
                .setContentText("Hello World!");
        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }
    private void sendActivity(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(),"")
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_notifications))
                .setContentTitle("My notification")
                .setContentText("Hello World!");
        Intent resultIntent = new Intent(getContext(),NotificationInfoActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
        stackBuilder.addParentStack(NotificationInfoActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }
}
