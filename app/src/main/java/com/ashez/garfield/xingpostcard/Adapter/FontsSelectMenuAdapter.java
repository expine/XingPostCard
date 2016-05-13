package com.ashez.garfield.xingpostcard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ashez.garfield.xingpostcard.R;

/**
 * Created by Garfield on 5/12/16.
 */
public class FontsSelectMenuAdapter extends BaseAdapter {

    private Context mContext;

    String [] menulist = {"默认" , "正方幼线" , "2" , "3" , "4", "5","6" };

    public FontsSelectMenuAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 70;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (null==view){
            holder = new Holder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_fontsselectmenu, null);
            holder.tv = (TextView) view.findViewById(R.id.item_tv_fonts);
            view.setTag(holder);
        }else {
            holder = (Holder) view.getTag();
        }
        holder.tv.setText(menulist[1]);
        return view;
    }
    private class Holder {
        TextView tv;
    }
}
