package com.zhy.uutils.view_custome;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wanyummy on 2017/7/6.
 */

public class ZdyView extends View {

    private Paint mPaint;
    private Rect mBounds;
    private String data = "项 目";

    public ZdyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(10, 10, getWidth(), 10, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(10, 10, 10, getHeight(), mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawLine(10, getHeight(), getWidth(), getHeight(), mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(getWidth(), 10, getWidth(), getHeight(), mPaint);


        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(30);
        //获取文字的大小
        mPaint.getTextBounds(data, 0, data.length(), mBounds);

        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        //在view上写字
        canvas.drawText(data, getWidth() / 2 - textWidth / 2, getHeight() / 2
                + textHeight / 2, mPaint);
    }
}
