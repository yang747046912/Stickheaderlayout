# Stickheaderlayout
stickheaderlayout

## maven 引用
```
<dependency>
  <groupId>com.sys.blackcat.stickheaderlayout</groupId>
  <artifactId>library</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```
-------------------
## Gradle 引用
compile 'com.sys.blackcat.stickheaderlayout:library:1.0.1'

### [ ![Download](https://api.bintray.com/packages/yang747046912/maven/StickHeaderLayout/images/download.svg) ](https://bintray.com/yang747046912/maven/StickHeaderLayout/_latestVersion)

# 自定义Stickheaderlayout介绍

####https://github.com/yang747046912/Stickheaderlayout/

---------------
#效果
![Stickheaderlayout](https://raw.githubusercontent.com/yang747046912/Stickheaderlayout/master/image/pp.gif)

#使用
##以ListView 为例子
####布局文件
```
<?xml version="1.0" encoding="utf-8"?>
<com.sys.blackcat.stickheaderlayout.StickHeaderLayout  	    xmlns:android="http://schemas.android.com/apk/res/android"
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
###Activity
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
----------
```
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
            return groupLocation[1] == itemLocation[1];
        }
        return false;
    }
```

#IpmlScrollChangListener 介绍
包含四个方法

 - boolean isReadyForPull()
	是否可以将head拉下来
 - void onStartScroll()
    滚动开始
 - void onStopScroll()
   滚动开始
 - void onScrollChange(int dy, int totallDy)
  滚动距离的变化 dy:滚动的距离  totallDy:可滚动的最大距离
 配合 setRetentionHeight(int )可以用于做渐变的titlebar （见效果图）
#StickHeaderLayout 方法介绍
	
 - setRetentionHeight(int retentionHeight)
   设置向上滚动headView 保留的高度
 - setScroll(IpmlScrollChangListener scroll)
 设置滚动回调

	

