package com.zhy.uutils.drag_view_finish;

/**
 * @author zhy
 * @time 2018/1/17 下午4:13
 * @description
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhy.uutils.R;

public class MainActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drag);

        findViewById(R.id.buttonDragH).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DragActivity.class);
                intent.putExtra("horizontal", true);
                startActivity(intent);
            }
        });
        findViewById(R.id.buttonDragV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DragActivity.class);
                intent.putExtra("vertical", true);
                startActivity(intent);
            }
        });
        findViewById(R.id.buttonDragEdge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DragActivity.class);
                intent.putExtra("edge", true);
                startActivity(intent);
            }
        });
        findViewById(R.id.buttonDragCapture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DragActivity.class);
                intent.putExtra("capture", true);
                startActivity(intent);
            }
        });
        findViewById(R.id.buttonYoutube).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, YoutubeActivity.class);
                startActivity(intent);
            }
        });
    }
}