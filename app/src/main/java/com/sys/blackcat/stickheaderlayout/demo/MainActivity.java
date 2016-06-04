package com.sys.blackcat.stickheaderlayout.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_list:
                openActivty(ListDemo.class);
                break;
            case R.id.btn_grid:
                openActivty(GridDemo.class);
                break;
            case R.id.btn_scrollview:
                openActivty(ScrollViewDemo.class);
                break;
            case R.id.btn_recycler_view:
                openActivty(RecyclerViewDemo.class);
                break;
        }
    }

    private void openActivty(Class cl) {
        Intent intent = new Intent(this, cl);
        startActivity(intent);
    }
}
