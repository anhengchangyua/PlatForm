package com.zhy.downloadlib.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;

import com.blankj.utilcode.util.AppUtils;
import com.zhy.downloadlib.model.WpsModel;

import java.io.File;

/**
 * Created by wanyummy on 2017/8/22.
 */

public class OpenWpsUtil {

    public String uri;
    public Context mContext;

    public OpenWpsUtil(String uri, Context context) {
        this.uri = uri;
        this.mContext = context;
    }

    public static OpenWpsUtil create(String mUri, Context context) {
        return new OpenWpsUtil(mUri, context);
    }

    public boolean openFileWithWps() {
        return open(uri, mContext);
    }

    static boolean open(String path, Context mContext) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(WpsModel.OPEN_MODE, WpsModel.OpenMode.NORMAL); // 打开模式
        bundle.putBoolean(WpsModel.SEND_CLOSE_BROAD, true); // 关闭时是否发送广播
        bundle.putString(WpsModel.THIRD_PACKAGE, AppUtils.getAppPackageName()); // 第三方应用的包名，用于对改应用合法性的验证
        bundle.putBoolean(WpsModel.CLEAR_TRACE, true);// 清除打开记录
        // bundle.putBoolean(CLEAR_FILE, true); //关闭后删除打开文件
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setClassName(WpsModel.PackageName.NORMAL, WpsModel.ClassName.NORMAL);

        File file = new File(path);
        if (file == null || !file.exists()) {
            System.out.println("文件为空或者不存在");
            return false;
        }
        Uri uri = FileProvider.getUriForFile(mContext, "com.finstone.cz_treasury.fileprovider", file);
        intent.setData(uri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intent.putExtras(bundle);
        try {
            mContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            System.out.println("打开wps异常：" + e.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
