package com.ashez.garfield.xingpostcard.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashez.garfield.xingpostcard.Adapter.ArtMenuAdapter;
import com.ashez.garfield.xingpostcard.R;
import com.ashez.garfield.xingpostcard.Utils.A;
import com.ashez.garfield.xingpostcard.Utils.MyImageView;
import com.ashez.garfield.xingpostcard.Utils.Ruler;

import java.io.ByteArrayOutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Garfield on 4/26/16.
 */
public class ArtActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    ArtMenuAdapter artMenuAdapter;
    Handler mHandler;

    @Bind(R.id.pic_edit_view)
    MyImageView imageView;
    @Bind(R.id.words_1)
    TextView mwords;

    @Bind(R.id.art_menu)
    RecyclerView artMenu;
    @Bind(R.id.art_picsarea)
    LinearLayout artPicsarea;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_art);
        ButterKnife.bind(this);


        initHandler();
        initImageView();//初始化Imageview
        initTextViewAndEditView();//对文字框编辑框的初始化，位置微调
        iniMenu();//初始化底部





    }

    private void initHandler() {
        mHandler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0x00:
                        mwords.setTypeface(Typeface.DEFAULT);
                        break;
                    case 0x01:
                        mwords.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/zfyx.ttf"));
                        break;
                    case 0x02:
                        /**
                         * 编辑完后，从编辑界面跳转到结果界面
                         * */
                        // 此处获取剪裁后的bitmap
                        Bitmap bitmap = imageView.clip();

                        // 由于Intent传递bitmap不能超过40k,此处使用二进制数组传递
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] bitmapByte = baos.toByteArray();

                        Intent intent = new Intent(ArtActivity.this, ResultActivity.class);
                        intent.putExtra("bitmap", bitmapByte);
                        startActivity(intent);

                }
            }
        };
    }

    private void iniMenu() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        artMenu.setLayoutManager(llm);
        artMenuAdapter = new ArtMenuAdapter(this, mHandler);
        artMenu.setAdapter(artMenuAdapter);
    }



    private void initTextViewAndEditView() {
        /**该方法用于微调文字框的位置*/

        Intent intent = getIntent();

        if (intent.getStringExtra("words") != null) {
            mwords.setText(intent.getStringExtra("words"));
        }
        mwords.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/zfyx.ttf"));

        /**
         * 调整编辑界面图片的比例
         * */



//        artPicsarea.setLayoutParams();


    }


//    private class MyGetFocusListener implements View.OnClickListener {
//        @Override
//        public void onClick(View view) {
////    用于让BlankView获得焦点，并让键盘消失。
//            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(artWordsEt.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
////            InputMethodManager imm = (`InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
////            if (imm.isActive()) {
////                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
////            }
//            Log.e("eee", "-------------哦！你按到我了！");
//
////            view.requestFocus();
//        }
//    }


    private void initImageView() {
        ViewTreeObserver vto2 = imageView.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                imageView.setBorderWeight(imageView.getWidth(), imageView.getHeight());
                Log.e("hhhhhhhh", imageView.getHeight() + "," + imageView.getWidth());
            }
        });

        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.img));
//        imageView.clip();
    }


    @OnClick({R.id.pic_edit_view, R.id.words_1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pic_edit_view:
                //
                break;
            case R.id.words_1:
                /**点击文字后，编辑框的出现*/
                A.goOtherActivityNoAnim(this, EditCardActivity.class);
                break;
        }
    }


    @Override
    public void onFocusChange(View view, boolean b) {
    }
}
