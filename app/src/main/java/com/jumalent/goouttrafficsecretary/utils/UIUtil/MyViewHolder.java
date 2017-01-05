package com.jumalent.goouttrafficsecretary.utils.uiutil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by seeroo_dev on 2017. 1. 5..
 */
public class MyViewHolder extends BaseViewHolder<String> {
    private TextView mStTextView;
    private TextView mEnTextView;

    public static MyViewHolder newInstance(ViewGroup parent){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new MyViewHolder(itemView);
    }

    public MyViewHolder(View itemView){
        super(itemView);
        mStTextView = (TextView) itemView.findViewById(android.R.id.text1);
        mEnTextView = (TextView) itemView.findViewById(android.R.id.text2);
    }

    @Override
    public void onBindView(String item) {
        mStTextView.setText(item);
        mEnTextView.setText(item);
    }
}