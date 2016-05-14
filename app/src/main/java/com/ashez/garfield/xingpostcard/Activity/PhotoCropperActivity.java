package com.ashez.garfield.xingpostcard.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashez.garfield.xingpostcard.Adapter.ArtMenuAdapter;
import com.ashez.garfield.xingpostcard.Constants.State;
import com.ashez.garfield.xingpostcard.R;
import com.ashez.garfield.xingpostcard.Utils.A;
import com.ashez.garfield.xingpostcard.Utils.Ruler;
import com.ashez.garfield.xingpostcard.Utils.SaveFile;
import com.githang.clipimage.ClipImageView;

/**
 * Created by Garfield on 5/15/16.
 */
public class PhotoCropperActivity extends AppCompatActivity {
    com.ashez.garfield.xingpostcard.Utils.ClipImageView clipImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photocropper);
//        ClipImageView clipImageView = (ClipImageView) findViewById(R.id.clip_image_view);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.cropper_layout);


        clipImageView = new com.ashez.garfield.xingpostcard.Utils.ClipImageView(this, getIntent().getIntExtra("ivWidth", -1), getIntent().getIntExtra("ivHeight", -1));
        clipImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bg01));


        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );

        assert frameLayout != null;
        frameLayout.addView(clipImageView);
        System.out.println("1111111111" + getIntent().getStringExtra("pictureFromLocal"));

//                View view =View.inflate(ll.getContext(), R.layout.pattern_1, ll);
        findViewById(R.id.cropper_bt_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = SaveFile.toSaveFile(clipImageView.clip(), "crops");
                Intent intent = new Intent(PhotoCropperActivity.this, ArtActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("path", path);//把剪完的图片存储地址传过去
                intent.putExtra("styleCode", getIntent().getStringExtra("styleCode"));
                State.isneedtocrop = false;
                A.goOtherActivityFinishNoAnim(PhotoCropperActivity.this, intent);
            }
        });

        findViewById(R.id.cropper_bt_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhotoCropperActivity.this, CollectionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                A.goOtherActivityFinishNoAnim(PhotoCropperActivity.this, intent);
            }
        });


//        imageView = (ImageView) view.findViewById(R.id.pic_edit_view);
//        mwords = (TextView) view.findViewById(R.id.words_1);


    }
}
