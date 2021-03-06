# Stickheaderlayout
stickheaderlayout

## maven 引用
```
<dependency>
  <groupId>com.sys.blackcat.stickheaderlayout</groupId>
  <artifactId>library</artifactId>
  <version>1.3.5</version>
  <type>pom</type>
</dependency>
```
-------------------
## Gradle 引用
compile 'com.sys.blackcat.stickheaderlayout:library:1.3.4'

### [ ![Download](https://api.bintray.com/packages/yang747046912/maven/StickHeaderLayout/images/download.svg) ](https://bintray.com/yang747046912/maven/StickHeaderLayout/_latestVersion)


#自定义Stickheaderlayout介绍
##效果
![Stickheaderlayout](https://raw.githubusercontent.com/yang747046912/Stickheaderlayout/master/image/pp.gif)
##原理
Stickheaderlayout 继承 ViewGroup ，总体分为三块
- 头部
  可以放置上下不可滚动的view
- 标题
  可以放置上下不可滚动的view（不想出现标题则可以仅放一条横线）
- 内容
  可以放置GridView、ListView、RecyclerView、ScrollView、ViewPager并且兼容下拉刷新跟上拉自动加载更多

使用ViewDragHelper跟View事件的拦截相关的东西，ViewDragHelper不了解的可访问http://www.jianshu.com/p/bfe857e509c9

##IpmlScrollChangListener 介绍
包含四个方法 
- boolean isReadyForPull()
是否可以将head拉下来 
- void onStartScroll() 
滚动开始 
- void onStopScroll()
 滚动开始 
- void onScrollChange(int dy, int totallDy) 
滚动距离的变化 dy:滚动的距离 totallDy:可滚动的最大距离 配合 setRetentionHeight(int )可以用于做渐变的titlebar （见viewPagerDemo效果图）

##StickHeaderLayout 方法介绍  
- setRetentionHeight(int retentionHeight) 
设置向上滚动headView 保留的高度 
- setScroll(IpmlScrollChangListener scroll) 
设置滚动回调

##ViewDragHelper初始化
- 滚动状态监听
```
 public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            if (scroll != null) {
                if (state == ViewDragHelper.STATE_IDLE) {
                    scroll.onStopScroll();
                }
                if (state == ViewDragHelper.STATE_SETTLING) {
                    scroll.onStartScroll();
                }
            }
        }
```
- 判断ViewDragHelper是否触发滚动 头部、标题、内容都可以滑动
```
public boolean tryCaptureView(View child, int pointerId) {
           return true;
        }
```
- 手指按住contentView时滚动时，头部、标题、内容的位置变化,返回contentView是移动距离
```
public int clampViewPositionVertical(View child, int top, int dy) {
            if (child == headView) {
                int headViewTop = headView.getTop() + dy;
                if (headViewTop > 0) {
                    headViewTop = 0;
                } else if (headViewTop < -headHeight + retentionHeight) {
                    headViewTop = -headHeight + retentionHeight;
                }
                return headViewTop;
            }
            if (child == titleView) {
                int titleTop = titleView.getTop() + dy;
                if (titleTop > headHeight) {
                    titleTop = headHeight;
                } else if (titleTop < retentionHeight) {
                    titleTop = retentionHeight;
                }
                return titleTop;
            }
            int contentTop = contentView.getTop() + dy;
            if (contentTop > headHeight + titleHeight) {
                contentTop = headHeight + titleHeight;
            } else if (contentTop < titleHeight + retentionHeight) {
                contentTop = titleHeight + retentionHeight;
            }
            return contentTop;
        }
```
- 手指离开屏幕时，触发快速滚动
```
public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (dragEdge == DragEdge.Bottom) {
                if (yvel < -mDragHelper.getMinVelocity()) {
                    mDragHelper.smoothSlideViewTo(contentView, 0, titleHeight + retentionHeight);
                } else if (releasedChild == headView) {
                    mDragHelper.smoothSlideViewTo(headView, 0, -headHeight + retentionHeight);
                } else {
                    mDragHelper.smoothSlideViewTo(titleView, 0, retentionHeight);
                }
            } else if (dragEdge == DragEdge.Top) {
                if (releasedChild == contentView) {
                    mDragHelper.smoothSlideViewTo(contentView, 0, titleHeight + headHeight);
                } else if (releasedChild == headView) {
                    mDragHelper.smoothSlideViewTo(headView, 0, 0);
                } else {
                    mDragHelper.smoothSlideViewTo(titleView, 0, headHeight);
                }
            }
            invalidate();
        }
```
- 触发快速滚动后头部、标题、内容的位置变化
```
public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            if (changedView == contentView) {
                int contentTop = contentView.getTop();
                titleView.layout(0, contentTop - titleHeight, titleView.getMeasuredWidth(), contentTop);
                headView.layout(0, contentTop - titleHeight - headHeight, titleView.getMeasuredWidth(), contentTop - titleHeight);
            } else if (changedView == headView) {
                int contentTop = headView.getTop();
                titleView.layout(0, contentTop + headHeight, titleView.getMeasuredWidth(), contentTop + headHeight + titleHeight);
                contentView.layout(0, contentTop + headHeight + titleHeight, contentView.getMeasuredWidth(), contentTop + headHeight + titleHeight + contentHeight);
            } else {
                int contentTop = titleView.getTop();
                headView.layout(0, contentTop - headHeight, headView.getMeasuredWidth(), contentTop);
                contentView.layout(0, contentTop + titleHeight, contentView.getMeasuredWidth(), contentTop + titleHeight + contentHeight);
            }
            if (scroll != null) {
                scroll.onScrollChange(Math.abs(headView.getTop()), headHeight - retentionHeight);
            }
        }
```

##手势的相关处理
- 事件的拦截
```
public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDragHelper.processTouchEvent(ev);
                sX = ev.getRawX();
                sY = ev.getRawY();
                mIsBeingDragged = false;
                break;
            case MotionEvent.ACTION_MOVE:
                checkCanDrag(ev);
                if (dragEdge == DragEdge.Left || dragEdge == DragEdge.Right || dragEdge == DragEdge.None) {
                    return false;
                }
                if (scroll != null && !scroll.isReadyForPull()) {
                    if (dragEdge == DragEdge.Bottom && titleView.getTop() == headHeight) {
                        return true;
                    }
                    return false;
                } else {
                    if (dragEdge == DragEdge.Bottom) {
                        if (titleView.getTop() == retentionHeight) {
                            mIsBeingDragged = false;
                            dragEdge = DragEdge.None;
                        }
                    } else if (dragEdge == DragEdge.Top) {
                        if (titleView.getTop() == headHeight) {
                            dragEdge = DragEdge.None;
                            mIsBeingDragged = false;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsBeingDragged = false;
                mDragHelper.processTouchEvent(ev);
                break;
            default:
                mDragHelper.processTouchEvent(ev);
                break;
        }
        return mIsBeingDragged;
    }
```
- 判断手势的滑动方向
```
public enum DragEdge {
        None,
        Left,
        Top,
        Right,
        Bottom
    }
private void checkCanDrag(MotionEvent ev) {
        float dx = ev.getRawX() - sX;
        float dy = ev.getRawY() - sY;
        float angle = Math.abs(dy / dx);
        angle = (float) Math.toDegrees(Math.atan(angle));
        if (angle < 45) {
            if (dx > 0) {
                dragEdge = DragEdge.Left;
            } else if (dx < 0) {
                dragEdge = DragEdge.Right;
            }
        } else {
            if (dy > 0) {
                dragEdge = DragEdge.Top;
            } else if (dy < 0) {
                dragEdge = DragEdge.Bottom;
            }
        }
        mIsBeingDragged = true;
    }
```
- 事件的处理
``` 
 public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDragHelper.processTouchEvent(event);
            case MotionEvent.ACTION_MOVE: {
                checkCanDrag(event);
                if (mIsBeingDragged) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    try {
                        mDragHelper.processTouchEvent(event);
                    } catch (Exception e) {
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsBeingDragged = false;
                mDragHelper.processTouchEvent(event);
                break;
            default://handle other action, such as ACTION_POINTER_DOWN/UP
                mDragHelper.processTouchEvent(event);
        }
        return super.onTouchEvent(event) || mIsBeingDragged || action == MotionEvent.ACTION_DOWN;
    }
```

##使用 以ListView 为例子
- 布局文件
```
<?xml version="1.0" encoding="utf-8"?>
<com.sys.blackcat.stickheaderlayout.StickHeaderLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:id="@+id/stick_header_layout"
    android:layout_height="match_parent">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="100dp"
        android:background="@color/red"
        android:gravity="center"
        android:text="我是头部"
        android:textColor="@color/white" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="40dp"
        android:background="@color/blue"
        android:gravity="center"
        android:text="我是标题"
        android:textColor="@color/white" />
    <!--内容-->
    <ListView
        android:id="@+id/list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/green" />
</com.sys.blackcat.stickheaderlayout.StickHeaderLayout>
```
- Activity 逻辑处理
```
public class ListViewDemo extends AppCompatActivity {
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
```
- DemoUtils  判断内容部分是否可以滑动
```
public static boolean isOnTop(ViewGroup viewGroup) {
        int[] groupLocation = new int[2];
        viewGroup.getLocationOnScreen(groupLocation);
        int[] itemLocation = new int[2];
        if (viewGroup.getChildAt(0) != null) {
            viewGroup.getChildAt(0).getLocationOnScreen(itemLocation);
            return groupLocation[1] == itemLocation[1];
        }
        return false;
    }
```

