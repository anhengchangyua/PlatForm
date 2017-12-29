package com.zhy.uutils.scheme_preview.util;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.zhy.uutils.R;
import com.zhy.uutils.view_custome.MainActivity;

/**
 * Created by wanyummy on 2017/12/29.
 */

public class NotifyUtil {

    private static int COUNT = 1;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void createNotify(Context context, String url, String title) {

        Intent intent = JumpUtil.parseIntent(context, url, title);

        PendingIntent pendingIntent;


        if (!ViewUtil.isLauncherActivity(context, MainActivity.class)) {
            TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
            taskStackBuilder
                    .addParentStack(intent.getComponent())
                    .addNextIntent(intent).startActivities();

            pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
        } else {
            pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        //这里没有背景透明的图片。
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle(
                                TextUtils.isEmpty(title) ? context.getResources().getString(R.string.app_name) : title)
                        .setContentText("测测")
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .setTicker("测测试试");

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setAutoCancel(true);

        int mNotificationId = 1212 + COUNT++;
        NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

}
