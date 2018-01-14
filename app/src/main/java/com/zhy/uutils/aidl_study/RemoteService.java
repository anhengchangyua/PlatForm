package com.zhy.uutils.aidl_study;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;

/**
 * @author zhy
 * @time 2018/1/14 下午3:33
 * @description
 */

public class RemoteService extends Service {
    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder; //暴露给客户端
    }

    // 实现AIDL接口
    private final IRemoteService.Stub binder = new IRemoteService.Stub() {


        @Override
        public int getPid() throws RemoteException {
            return 0;
        }

        @Override
        public MyProcess getProcess(MyProcess clientProcess) throws RemoteException {
            MyProcess process = new MyProcess(Process.myPid(), getCurProcessName(RemoteService.this));
            return process;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                               double aDouble, String aString) throws RemoteException {

        }
    };

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}

