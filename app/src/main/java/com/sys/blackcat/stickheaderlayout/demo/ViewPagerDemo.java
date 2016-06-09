package com.sys.blackcat.stickheaderlayout.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sys.blackcat.stickheaderlayout.IpmlScrollChangListener;
import com.sys.blackcat.stickheaderlayout.StickHeaderLayout;
import com.sys.blackcat.stickheaderlayout.demo.adapter.FragmentAdapter;
import com.sys.blackcat.stickheaderlayout.demo.fragment.GridViewFragment;
import com.sys.blackcat.stickheaderlayout.demo.fragment.ListViewFragment;
import com.sys.blackcat.stickheaderlayout.demo.fragment.RecyclerViewFragment;
import com.sys.blackcat.stickheaderlayout.demo.fragment.ScrollViewFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerDemo extends AppCompatActivity {

    private StickHeaderLayout layout;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_demo);
        setTitle(R.string.viewPager_example);
        layout = (StickHeaderLayout) findViewById(R.id.stick_header_layout);
        adapter = new FragmentAdapter(getSupportFragmentManager(), getFragment());
        viewPager = (ViewPager) findViewById(R.id.vp_view_pager);
        txt = (TextView) findViewById(R.id.tv_txt);
        layout.setScroll(new IpmlScrollChangListener() {
            private int rColor = 0xff;
            private int gColor = 0xff;
            private int bColor = 0x00;

            @Override
            public boolean isReadyForPull() {
                ViewGroup view = (ViewGroup) adapter.getItem(viewPager.getCurrentItem()).getView();
                if (view != null) {
                    return DemoUtils.isOnTop(view);
                }
                return true;
            }

            @Override
            public void onStartScroll() {

            }

            @Override
            public void onStopScroll() {

            }

            @Override
            public void onScrollChange(int dy, int totallDy) {
                int aColor = (dy * 255) / totallDy;
                txt.setBackgroundColor(Color.argb(aColor, rColor, gColor, bColor));
            }
        });
        viewPager.setAdapter(adapter);
    }


    private List<Fragment> getFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(new GridViewFragment());
        list.add(new ListViewFragment());
        list.add(new ScrollViewFragment());
        list.add(new RecyclerViewFragment());
        return list;
    }
}
