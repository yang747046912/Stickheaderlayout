package com.sys.blackcat.stickheaderlayout;


public interface IpmlScrollChangListener {
    public boolean isReadyForPull();

    public void onStartScroll();

    public void onStopScroll();

    public void onScrollChange(int dy, int totallDy);
}
