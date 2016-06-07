package com.sys.blackcat.stickheaderlayout;


public interface IpmlScrollChangListener {
    /**
     * 是否可以将head拉下来
     */
     boolean isReadyForPull();

    /**
     * 滚动开始
     */
     void onStartScroll();

    /**滚动结束*/
     void onStopScroll();

    /**
     * 滚动距离的变化
     * @param dy    滚动的距离
     * @param totallDy 滚动的最大距离
     */
     void onScrollChange(int dy, int totallDy);
}
