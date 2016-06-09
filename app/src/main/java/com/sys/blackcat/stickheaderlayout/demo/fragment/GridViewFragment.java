package com.sys.blackcat.stickheaderlayout.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.sys.blackcat.stickheaderlayout.demo.DemoUtils;

/**
 * Created by Administrator on 2016/6/9.
 */
public class GridViewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        GridView mGridView = new GridView(getActivity());
        mGridView.setNumColumns(4);
        mGridView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));
        mGridView.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, DemoUtils.getData()));
        return mGridView;
    }
}
