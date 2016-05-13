package com.ashez.garfield.xingpostcard.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashez.garfield.xingpostcard.R;
import com.ashez.garfield.xingpostcard.Utils.A;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;

import java.io.ByteArrayOutputStream;


/**
 * Created by Garfield on 5/10/16.
 */
public class ArtMenuAdapter extends RecyclerView.Adapter<ArtMenuAdapter.MyViewHolder> {

    private Context context;
    private Handler mHandler;
    private int i = -1;

    public ArtMenuAdapter(Context context,Handler mHandler) {
        this.mHandler = mHandler;
        this.context = context;
    }






    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_artmenu, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        switch (position) {
            case 0:
                holder.iv.setImageResource(R.drawable.ic_done_black_24dp);
                holder.iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogPlus dialog = DialogPlus.newDialog(context)
                                .setAdapter(new FontsSelectMenuAdapter(context))
                                .setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                                        switch (position){
                                            case 0:
                                                mHandler.sendEmptyMessage(0x00);
                                                break;
                                            case 1:
                                                mHandler.sendEmptyMessage(0x01);
                                                break;
                                        }
                                        dialog.dismiss();
                                    }
                                })
                                .setExpanded(true,500).setGravity(Gravity.CENTER).setHeader(View.inflate(context,R.layout.item_fontsselectmenu,null)) // This will enable the expand feature, (similar to android L share dialog)
                                .create();
                        dialog.show();
                    }
                });

                break;
            case 1:
                holder.iv.setImageResource(R.drawable.ic_text_fields_black_24dp);
                break;
            case 2:
                holder.iv.setImageResource(R.drawable.ic_translate_black_24dp);
                break;
            case 3:
                holder.iv.setImageResource(R.drawable.ic_done_black_24dp);
                holder.iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mHandler.sendEmptyMessage(0x02);
                    }
                });
                break;
        }
    }


    @Override
    public int getItemCount() {
        return 4;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.art_menu_iv);
        }


    }
}
