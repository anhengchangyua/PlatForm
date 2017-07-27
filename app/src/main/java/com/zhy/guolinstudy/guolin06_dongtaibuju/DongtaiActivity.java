package com.zhy.guolinstudy.guolin06_dongtaibuju;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.guolinstudy.R;

import java.util.ArrayList;

/**
 * Created by wanyummy on 2017/7/7.
 */

public class DongtaiActivity extends AppCompatActivity {

    public ArrayList<LayoutEntity> datas = new ArrayList<>();
    public RelativeLayout relative;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutEntity entity = new LayoutEntity();
        entity.text = "本月收入";
        entity.width = dip2px(this, 300);
        entity.height = dip2px(this, 35);
        entity.textSize = dip2px(this, 15);;
        entity.type = "top";
        datas.add(entity);

        LayoutEntity entity2 = new LayoutEntity();
        entity2.text = "金额";
        entity2.width = dip2px(this, 90);
        entity2.height =dip2px(this, 70);
        entity2.textSize = dip2px(this, 15);
        entity2.type = "low";

        datas.add(entity2);


        LayoutEntity entity3 = new LayoutEntity();
        entity3.text = "比上年同期增减";
        entity3.width = dip2px(this, 210);
        entity3.height =  dip2px(this, 35);
        entity3.textSize =  dip2px(this, 15);
        entity3.type = "right";
        entity3.marginTop = dip2px(this, 35);
        datas.add(entity3);


        LayoutEntity entity4 = new LayoutEntity();
        entity4.text = "金额";
        entity4.width = dip2px(this, 105);
        entity4.height =  dip2px(this, 35);
        entity4.textSize =  dip2px(this, 15);
        entity4.type = "jin";
        entity4.marginLeft = dip2px(this, 90);
        datas.add(entity4);

        LayoutEntity entity5 = new LayoutEntity();
        entity5.text = "比率";
        entity5.width = dip2px(this, 105);
        entity5.height =  dip2px(this, 35);
        entity5.textSize =  dip2px(this, 15);
        entity5.type = "jin";
        entity5.marginLeft = dip2px(this, 195);
        datas.add(entity5);



        LayoutEntity entity6 = new LayoutEntity();
        entity6.text = "本月收入";
        entity6.width = dip2px(this, 300);
        entity6.height = dip2px(this, 35);
        entity6.textSize = dip2px(this, 15);;
        entity6.type = "topp";
        entity6.marginLeft = dip2px(this, 500);
        datas.add(entity6);

//        LayoutEntity entity7 = new LayoutEntity();
//        entity7.text = "金额";
//        entity7.width = dip2px(this, 90);
//        entity7.height =dip2px(this, 70);
//        entity7.textSize = dip2px(this, 15);
//        entity7.type = "low";
//
//        datas.add(entity7);
//
//
//        LayoutEntity entity8 = new LayoutEntity();
//        entity8.text = "比上年同期增减";
//        entity8.width = dip2px(this, 210);
//        entity8.height =  dip2px(this, 35);
//        entity8.textSize =  dip2px(this, 15);
//        entity8.type = "right";
//        entity8.marginTop = dip2px(this, 35);
//        datas.add(entity8);
//
//
//        LayoutEntity entity9 = new LayoutEntity();
//        entity9.text = "金额";
//        entity9.width = dip2px(this, 105);
//        entity9.height =  dip2px(this, 35);
//        entity9.textSize =  dip2px(this, 15);
//        entity9.type = "jin";
//        entity9.marginLeft = dip2px(this, 90);
//        datas.add(entity9);
//
//        LayoutEntity entity0 = new LayoutEntity();
//        entity0.text = "比率";
//        entity0.width = dip2px(this, 105);
//        entity0.height =  dip2px(this, 35);
//        entity0.textSize =  dip2px(this, 15);
//        entity0.type = "jin";
//        entity0.marginLeft = dip2px(this, 195);
//        datas.add(entity0);


        initUI();

    }

    private void initUI() {

        int mainy = dip2px(this, 105);
        int textViewx = dip2px(this, 120);
        int textSize = dip2px(this, 17);
        int relativeX = dip2px(this, 300);


        HorizontalScrollView main = new HorizontalScrollView(this);
        main.setLayoutParams(new LinearLayoutCompat.LayoutParams(LayoutParams.WRAP_CONTENT, mainy));
        main.setBackgroundColor(Color.WHITE);

        //根布局参数
        LinearLayout.LayoutParams layoutParamsRoot = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mainy);
        LinearLayout layoutRoot = new LinearLayout(this);
        layoutRoot.setLayoutParams(layoutParamsRoot);
        layoutRoot.setOrientation(LinearLayout.HORIZONTAL);

        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayoutCompat.LayoutParams(textViewx, mainy));
        textView.setText("项 目");
        textView.setTextSize(textSize);
        textView.setBackgroundResource(R.drawable.bg_table_header_kuang);
        textView.setGravity(Gravity.CENTER);
        layoutRoot.addView(textView);

        //RelativeLayout布局参数
        RelativeLayout.LayoutParams layoutParamsBottom = new RelativeLayout.LayoutParams(relativeX, RelativeLayout.LayoutParams.MATCH_PARENT);

        relative = new RelativeLayout(this);
        relative.setLayoutParams(layoutParamsBottom);
        relative.setBackgroundResource(R.drawable.bg_table_header_kuang);

        addLayout(datas);
        layoutRoot.addView(relative);

        main.addView(layoutRoot);
        setContentView(main);

    }

    private void addLayout(ArrayList<LayoutEntity> datas) {
        for (int i = 0; i < datas.size(); i++) {

            RelativeLayout.LayoutParams paramsImageBottom = new RelativeLayout.LayoutParams(datas.get(i).width, datas.get(i).height);
            if (datas.get(i).type.equals("low")) {
                paramsImageBottom.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                paramsImageBottom.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
            } else if (datas.get(i).type.equals("right")) {
                paramsImageBottom.setMargins(0,datas.get(i).marginTop,0,0);
                paramsImageBottom.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
            }else if (datas.get(i).type.equals("jin")) {
                paramsImageBottom.setMargins(datas.get(i).marginLeft,0,0,0);
                paramsImageBottom.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            }else if (datas.get(i).type.equals("topp")) {
                paramsImageBottom.setMargins(datas.get(i).marginLeft,0,0,0);
            }

            TextView textView = new TextView(this);
            textView.setText(datas.get(i).text);
            textView.setTextSize(datas.get(i).textSize);
            textView.setBackgroundResource(R.drawable.bg_table_header_kuang);
            textView.setGravity(Gravity.CENTER);
            relative.addView(textView, paramsImageBottom);
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
