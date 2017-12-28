package com.zhy.uutils.hll_core.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.zhy.uutils.hll_core.delegates.HLDelegate;

/**
 * @author zwy.
 * @time 2017/7/29 13:48
 * @description
 */
public abstract class BottomItemDelegate extends HLDelegate implements View.OnKeyListener {
    private long mExitTimes = 0;
    private static final int EXIT_TIME = 2000;


    //2 在resume的时候要重新监听 key
    @Override
    public void onResume() {
        super.onResume();
        View rootView = getView();
        if (rootView != null) {
            rootView.setFocusable(true);
            rootView.setFocusableInTouchMode(true);
            rootView.setOnKeyListener(this);
        }
    }

    //1
    @Override
    public boolean onKey(View view, int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTimes > EXIT_TIME) {
                Toast.makeText(getContext(), "双击推出", Toast.LENGTH_LONG).show();
                mExitTimes = System.currentTimeMillis();
            } else {
                _mActivity.finish();
                if (mExitTimes != 0) {
                    mExitTimes = 0;
                }
            }
            return true;
        }
        return false;
    }
}





