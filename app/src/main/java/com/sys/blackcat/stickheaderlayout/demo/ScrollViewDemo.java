package com.sys.blackcat.stickheaderlayout.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;

import com.sys.blackcat.stickheaderlayout.IpmlScrollChangListener;
import com.sys.blackcat.stickheaderlayout.StickHeaderLayout;

public class ScrollViewDemo extends AppCompatActivity {


    private StickHeaderLayout layout;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        setTitle(R.string.scrollView_example);
        layout = (StickHeaderLayout) findViewById(R.id.stick_header_ladyout);
        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        layout.setScroll(new IpmlScrollChangListener() {
            @Override
            public boolean isReadyForPull() {
                return DemoUtils.isOnTop(scrollView);
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
    }
}
