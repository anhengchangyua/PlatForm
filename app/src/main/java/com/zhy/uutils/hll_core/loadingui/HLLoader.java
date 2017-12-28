package com.zhy.uutils.hll_core.loadingui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.uutils.R;
import com.zhy.uutils.hll_core.util.DimenUtil;

import java.util.ArrayList;

/**
 * Created by wanyummy on 2017/7/13.
 */

public class HLLoader {

    private static final int LOAD_SIZE_SCALE = 8;
    private static final int LOAD_OFFSET_SCALE = 10;

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    //
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    // 传入枚举类型的时候
    public static void showLoading(Context context, Enum<LoaderStyle> styleEnum) {
        showLoading(context, styleEnum.name());

    }

    public static void showLoading(Context context, String type) {

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView av = LoaderCreator.create(type, context);

        dialog.setContentView(av);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOAD_SIZE_SCALE;
            lp.height = deviceHeight / LOAD_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOAD_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;

        }
        LOADERS.add(dialog);
        dialog.show();
    }

    //加载默认的loading
    public static void showLoading(Context context) {

        showLoading(context, DEFAULT_LOADER);
    }

    //停止
    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {

            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }

}
