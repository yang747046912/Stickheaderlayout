package com.sys.blackcat.stickheaderlayout.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/9.
 */
public class MyAdapter extends RecyclerView.Adapter {

    private List<String> data = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TextView) (holder.itemView)).setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addAll(List<String> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
