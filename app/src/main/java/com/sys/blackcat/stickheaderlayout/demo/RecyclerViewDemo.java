package com.sys.blackcat.stickheaderlayout.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.sys.blackcat.stickheaderlayout.IpmlScrollChangListener;
import com.sys.blackcat.stickheaderlayout.StickHeaderLayout;
import com.sys.blackcat.stickheaderlayout.demo.adapter.MyAdapter;

public class RecyclerViewDemo extends AppCompatActivity {


    private StickHeaderLayout layout;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        setTitle(R.string.recyclerView_example);
        layout = (StickHeaderLayout) findViewById(R.id.stick_header_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layout.setScroll(new IpmlScrollChangListener() {
            @Override
            public boolean isReadyForPull() {
                return DemoUtils.isOnTop(recyclerView);
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

        MyAdapter myAdapter = new MyAdapter();
        myAdapter.addAll(DemoUtils.getData());
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        GridLayoutManager manager = new GridLayoutManager(this,3);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(myAdapter);
    }


}
