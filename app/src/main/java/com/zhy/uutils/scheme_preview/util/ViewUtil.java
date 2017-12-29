package com.zhy.uutils.scheme_preview.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.util.List;

/**
 * Created by wanyummy on 2017/12/29.
 */

public class ViewUtil {

    public static boolean isLauncherActivity(Context context, Class<?> clazz) {


        try {
            Intent intent = null;
            intent = new Intent(context, clazz);

            ComponentName componentName = intent.resolveActivity(context.getPackageManager());

            boolean flag = false;

            if (componentName != null) {
                ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningTaskInfo> runningServices = am.getRunningTasks(10);
                for (ActivityManager.RunningTaskInfo taskInfo : runningServices) {
                    if (taskInfo.baseActivity.equals(am)) {
                        flag = true;
                        break;
                    }
                }
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}