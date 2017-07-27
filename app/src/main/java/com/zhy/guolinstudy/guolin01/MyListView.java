package com.zhy.guolinstudy.guolin01;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zhy.guolinstudy.R;

/**
 * Created by wanyummy on 2017/7/5.
 */

public class MyListView extends ListView implements View.OnTouchListener, GestureDetector.OnGestureListener {
    public GestureDetector gestureDetector;
    public boolean isDeleteButton;
    public ViewGroup mViewGroup;
    public View mButton;
    public int selectedItem;
    private OnDeleteListener listener;

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(getContext(), this);
        setOnTouchListener(this);
    }

    // 重写listener
    public void setOnDeleteListener(OnDeleteListener l) {
        listener = l;
    }

    //当手指按下时，会调用OnGestureListener的onDown()方法
    @Override
    public boolean onDown(MotionEvent e) {
        if (!isDeleteButton) {
            selectedItem = pointToPosition((int) e.getX(), (int) e.getY());
        }

        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    //当手指快速滑动时，会调用onFling()方法,在这里会去加载delete_button.xml这个布局
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (!isDeleteButton && Math.abs(velocityX) > Math.abs(velocityY)) {
            mButton = LayoutInflater.from(getContext()).inflate(
                    R.layout.delete_button, null);
            mButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewGroup.removeView(mButton);
                    mButton = null;
                    isDeleteButton = false;
                    listener.onDelete(selectedItem);
                }
            });
            mViewGroup = (ViewGroup) getChildAt(selectedItem
                    - getFirstVisiblePosition());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            mViewGroup.addView(mButton, params);
            isDeleteButton = true;
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (isDeleteButton) {
            mViewGroup.removeView(mButton);
            mButton = null;
            isDeleteButton = false;
            return false;
        } else {
            return gestureDetector.onTouchEvent(event);
        }
    }

    public interface OnDeleteListener {

        void onDelete(int x);

    }
}
