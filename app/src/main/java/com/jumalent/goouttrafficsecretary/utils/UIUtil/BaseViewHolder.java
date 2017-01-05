package com.jumalent.goouttrafficsecretary.utils.uiutil;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by seeroo_dev on 2017. 1. 5..
 */
public abstract class BaseViewHolder<ITEM> extends RecyclerView.ViewHolder{
    public BaseViewHolder(View itemView){
        super(itemView);
    }

    public abstract void onBindView(ITEM item);
}
