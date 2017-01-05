package com.jumalent.goouttrafficsecretary.utils.uiutil;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seeroo_dev on 2017. 1. 5..
 */
public abstract class MultiItemAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    private List<Row<?>> mRows = new ArrayList<>();

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position){
        holder.onBindView(getItemId(position));
    }

    public void setRows(List<Row<?>> mRows){
        mRows.clear();
        mRows.addAll(mRows);
    }

    @Override
    public int getItemCount(){
        return mRows.size();
    }

    @Override
    public int getItemViewType(int position){
        return mRows.get(position).getItemViewType();
    }

    public static class Row<ITEM>{
        private ITEM item;
        private int itemViewType;

        public Row(ITEM item, int itemViewType){
            this.item = item;
            this.itemViewType = itemViewType;
        }

        public ITEM getItem(){
            return item;
        }

        public int getItemViewType() {
            return itemViewType;
        }
    }
}
