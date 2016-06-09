package com.sys.blackcat.stickheaderlayout.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sys.blackcat.stickheaderlayout.demo.DemoUtils;
import com.sys.blackcat.stickheaderlayout.demo.R;
import com.sys.blackcat.stickheaderlayout.demo.adapter.MyAdapter;

/**
 * Created by Administrator on 2016/6/9.
 */
public class RecyclerViewFragment extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view_fragment, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) getView();
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
