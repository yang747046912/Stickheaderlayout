package com.sys.blackcat.stickheaderlayout.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sys.blackcat.stickheaderlayout.IpmlScrollChangListener;
import com.sys.blackcat.stickheaderlayout.StickHeaderLayout;

public class ListDemo extends AppCompatActivity {

    private StickHeaderLayout layout;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demo);
        setTitle(R.string.list_example);
        layout = (StickHeaderLayout) findViewById(R.id.stick_header_layout);
        listView = (ListView) findViewById(R.id.list);
        layout.setScroll(new IpmlScrollChangListener() {
            @Override
            public boolean isReadyForPull() {
                return DemoUtils.isOnTop(listView);
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
        listView.addHeaderView(getLayoutInflater().inflate(R.layout.list_head, null));
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, DemoUtils.getData()));
    }
}
