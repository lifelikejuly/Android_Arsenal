package com.julyyu.utilslibrary.util;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

/**
 * Created by julyyu on 2017/6/14.
 * 消息通知工具
 */

public class NotificationUtils {

    /**
     * 简单通知
     * @param context
     * @param icon
     * @param id
     * @param title
     * @param content
     */
    public static void takeSimpleNotification(@NonNull Context context, @NonNull @DrawableRes int icon, @NonNull int id, @NonNull String title, @NonNull String content) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(icon)
                        .setContentTitle(title)
                        .setContentText(content);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, mBuilder.build());
    }

    /**
     *
     * @param context
     * @param pendingIntent
     * @param intent
     * @param icon
     * @param id
     * @param title
     * @param content
     */
    public static void takeIntentNotification(@NonNull Context context, @NonNull PendingIntent pendingIntent,Intent intent, @NonNull @DrawableRes int icon, @NonNull int id, @NonNull String title, @NonNull String content) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),icon))
                        .setContentTitle(title)
                        .setAutoCancel(true)
                        .setContentText(content)
                        .setContentIntent(pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setSmallIcon(icon)
                    .setFullScreenIntent(PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT), true);
        }
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = mBuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        mNotificationManager.notify(id, notification);
    }

    /**
     * 文本通知
     * @param context
     * @param pendingIntent
     * @param intent
     * @param icon
     * @param smallIcon
     * @param id
     * @param title
     * @param content
     * @param bigTextStyle
     */
    public static void takeIntentNotificationWithBigText(@NonNull Context context, @NonNull PendingIntent pendingIntent, Intent intent, @NonNull @DrawableRes int icon,@NonNull @DrawableRes int smallIcon,@NonNull int id, @NonNull String title, @NonNull String content, NotificationCompat.BigTextStyle bigTextStyle){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),icon))
                        .setContentTitle(title)
                        .setStyle(bigTextStyle)
                        .setAutoCancel(true)
                        .setContentText(content)
                        .setContentIntent(pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setSmallIcon(smallIcon);
//                    .setFullScreenIntent(PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT), true);
        }
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = mBuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        mNotificationManager.notify(id, notification);
    }

    public static NotificationCompat.BigTextStyle setBigTextStyle(String title,String bigText,String summaryText){
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(title);
        bigTextStyle.bigText(bigText);
        bigTextStyle.setSummaryText(summaryText);
        return bigTextStyle;
    }

    /**
     * 清空通知栏消息
     * @param application
     */
    public static void clearAllNotification(Application application){
        NotificationManager notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

}
