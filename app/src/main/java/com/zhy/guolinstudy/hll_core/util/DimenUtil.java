package com.zhy.guolinstudy.hll_core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.zhy.guolinstudy.hll_core.HLApplication;

/**
 * Created by wanyummy on 2017/7/13.
 */

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = HLApplication.getInstance().getApplicationContext().getResources();


        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = HLApplication.getInstance().getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
