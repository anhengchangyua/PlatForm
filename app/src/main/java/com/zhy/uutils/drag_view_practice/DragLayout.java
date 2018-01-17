package com.zhy.uutils.drag_view_practice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.zhy.uutils.R;

/**
 * @author zhy
 * @time 2018/1/17 下午4:25
 * @description
 */

public class DragLayout extends ViewGroup {

    private final ViewDragHelper mDragHelper;
    private View mFooterView;
    private View mDescView;


    private int mDragRange;
    private int mTop;
    private float mDragOffset;

    private float mInitialMotionX;
    private float mInitialMotionY;

    public DragLayout(Context context) {
        this(context, null);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragHelper = ViewDragHelper.create(this, 1f, new DragLayout.DragHelperCallback());

    }

    //获取需要使用的view

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onFinishInflate() {
        mDescView = findViewById(R.id.desc);
        mFooterView = findViewById(R.id.header_pro);
    }


    private class DragHelperCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mFooterView;
        }


        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            mTop = top;

            mDragOffset = (float) top / mDragRange;

//            mFooterView.setPivotX(mFooterView.getWidth());
//            mFooterView.setPivotY(mFooterView.getHeight());
//            mFooterView.setScaleX(1 - mDragOffset / 2);
//            mFooterView.setScaleY(1 - mDragOffset / 2);

            mFooterView.setAlpha(1 - mDragOffset);

            requestLayout();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int top = 300;
            if ( mDragOffset > 0.5f) {
                top = mDragRange;
            }
            //手指释放时
            mDragHelper.settleCapturedViewAt(0, top);
            invalidate();
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return mDragRange;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            final int bottomBound = getPaddingTop();
            final int bydoBound = getHeight() - mFooterView.getHeight() - mFooterView.getPaddingBottom();

            final int newTop = Math.min(Math.max(top, bottomBound),bydoBound);
            return newTop;
        }


    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);

        if ((action != MotionEvent.ACTION_DOWN)) {
            mDragHelper.cancel();
            return super.onInterceptTouchEvent(ev);
        }

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mDragHelper.cancel();
            return false;
        }

        final float x = ev.getX();
        final float y = ev.getY();
        boolean interceptTap = false;

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                mInitialMotionX = x;
                mInitialMotionY = y;
                interceptTap = mDragHelper.isViewUnder(mFooterView, (int) x, (int) y);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final float adx = Math.abs(x - mInitialMotionX);
                final float ady = Math.abs(y - mInitialMotionY);
                final int slop = mDragHelper.getTouchSlop();

                if (ady > slop && adx > ady) {
                    mDragHelper.cancel();
                    return false;
                }
            }
        }

        return mDragHelper.shouldInterceptTouchEvent(ev) || interceptTap;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mDragHelper.processTouchEvent(ev);

        final int action = ev.getAction();
        final float x = ev.getX();
        final float y = ev.getY();

        boolean isHeaderViewUnder = mDragHelper.isViewUnder(mFooterView, (int) x, (int) y);
        switch (action & MotionEventCompat.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                mInitialMotionX = x;
                mInitialMotionY = y;
                break;
            }

            case MotionEvent.ACTION_UP: {
                final float dx = x - mInitialMotionX;
                final float dy = y - mInitialMotionY;
                final int slop = mDragHelper.getTouchSlop();
                if (dx * dx + dy * dy < slop * slop && isHeaderViewUnder) {
                    if (mDragOffset == 0) {
//                        smoothSlideTo(1f);
                        ToastUtils.showShort("11111");
                    } else {
//                        smoothSlideTo(0f);
                        ToastUtils.showShort("22222");
                    }
                }
                break;
            }
        }

        return isHeaderViewUnder;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, 0),
                resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int height = mFooterView.getMeasuredHeight();
        int height1 = getHeight();
        mDragRange = getHeight() - mFooterView.getHeight();

        mDescView.layout(
                0,
                mTop,
                r,
                getHeight() - mFooterView.getMeasuredHeight() + mTop);

        mFooterView.layout(
                0,
                getHeight() - mFooterView.getMeasuredHeight() + mTop,
                r,
                getHeight());
    }
}
