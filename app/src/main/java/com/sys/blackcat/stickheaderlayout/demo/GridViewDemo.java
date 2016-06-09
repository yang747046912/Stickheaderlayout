package com.sys.blackcat.stickheaderlayout.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.sys.blackcat.stickheaderlayout.IpmlScrollChangListener;
import com.sys.blackcat.stickheaderlayout.StickHeaderLayout;

public class GridViewDemo extends AppCompatActivity {


    private StickHeaderLayout layout;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_demo);
        setTitle(R.string.grid_example);
        layout = (StickHeaderLayout) findViewById(R.id.stick_header_layout);
        gridView = (GridView) findViewById(R.id.grid);
        layout.setScroll(new IpmlScrollChangListener() {
            @Override
            public boolean isReadyForPull() {
                return DemoUtils.isOnTop(gridView);
            }

            @Override
            public void onStartScroll() {

            }

            @Override
            public void onStopScroll() {

            }

            @Override
            public void onScrollChange(int dy, int totallDy) {

            }
        });
        gridView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, DemoUtils.getData()));
    }
}
