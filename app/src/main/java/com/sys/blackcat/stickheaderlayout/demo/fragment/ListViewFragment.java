package com.sys.blackcat.stickheaderlayout.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sys.blackcat.stickheaderlayout.demo.DemoUtils;

/**
 * Created by Administrator on 2016/6/9.
 */
public class ListViewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ListView mListView = new ListView(getActivity());
        mListView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));
        mListView.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, DemoUtils.getData()));
        return mListView;
    }
}
