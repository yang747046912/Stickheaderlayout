package com.sys.blackcat.stickheaderlayout.demo;

import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangcai on 16-6-4.
 */
public class DemoUtils {

    public static List<String> getData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("我是第" + i + "条数据");
        }
        return data;
    }

    /**
     * 判断listView 是否可以滑动
     *
     * @param viewGroup
     * @return
     */
    public static boolean isOnTop(ViewGroup viewGroup) {
        int[] groupLocation = new int[2];
        viewGroup.getLocationOnScreen(groupLocation);
        int[] itemLocation = new int[2];
        if (viewGroup.getChildAt(0) != null) {
            viewGroup.getChildAt(0).getLocationOnScreen(itemLocation);
            Log.d("-->", "groupLocation y:" + groupLocation[1]);
            Log.d("-->", "itemLocation y:" + itemLocation[1]);
            return groupLocation[1] == itemLocation[1];
        }
        return false;
    }
}
