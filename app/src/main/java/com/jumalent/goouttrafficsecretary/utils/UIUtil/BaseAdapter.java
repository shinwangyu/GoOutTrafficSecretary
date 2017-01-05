package com.jumalent.goouttrafficsecretary.utils.uiutil;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seeroo_dev on 2017. 1. 5..
 */
public class BaseAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private List<String> mItems = new ArrayList<>();


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.onBindView(mItems.get(position));
    }

    public void setItems(List<String> items){
        mItems.clear();
        mItems.addAll(items);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}



