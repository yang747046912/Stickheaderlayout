package com.sys.blackcat.stickheaderlayout.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sys.blackcat.stickheaderlayout.demo.R;

/**
 * Created by Administrator on 2016/6/9.
 */
public class ScrollViewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.scroll_view_fragment, null);
    }
}
