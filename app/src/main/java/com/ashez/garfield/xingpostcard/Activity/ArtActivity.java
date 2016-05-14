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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashez.garfield.xingpostcard.Adapter.ArtMenuAdapter;
import com.ashez.garfield.xingpostcard.R;
import com.ashez.garfield.xingpostcard.Utils.A;

import java.io.ByteArrayOutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Garfield on 4/26/16.
 */
public class ArtActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private ArtMenuAdapter artMenuAdapter;
    private ViewGroup.LayoutParams layoutParams;
    private Handler mHandler;
    private int styleCode=0;

//    @Bind(R.id.pic_edit_view)
    ImageView imageView;
//    @Bind(R.id.words_1)
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


        initStyle(styleCode);





        initHandler();

        iniMenu();//初始化底部

        test();


    }

    private void test() {

        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        artPicsarea.measure(w, h);
        int height =artPicsarea.getMeasuredHeight();
        int width =artPicsarea.getMeasuredWidth();
        System.out.println("h="+height+"   w="+width);
    }

    private void initStyle(int styleCode) {
        switch (styleCode){
            case 0:

                LinearLayout ll = (LinearLayout) findViewById(R.id.art_picsarea);


//                View view =View.inflate(ll.getContext(), R.layout.pattern_1, ll);
                View view = LayoutInflater.from(this).inflate(R.layout.pattern_1, ll, true);
//                ll.addView(view);

                imageView = (ImageView) view.findViewById(R.id.pic_edit_view);
                mwords = (TextView) view.findViewById(R.id.words_1);

                initTextViewAndEditView();//对文字框编辑框的初始化，位置微调
                initImageView();//初始化Imageview

                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;




        }



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
                        imageView.setDrawingCacheEnabled(true);
                        Bitmap bitmap = imageView.getDrawingCache();


                        // 由于Intent传递bitmap不能超过40k,此处使用二进制数组传递
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] bitmapByte = baos.toByteArray();

                        Intent intent = new Intent(ArtActivity.this, ResultActivity.class);
                        intent.putExtra("bitmap", bitmapByte);
                        intent.putExtra("mwords", mwords.getText().toString());
                        startActivity(intent);
                        imageView.setDrawingCacheEnabled(false);

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
        mwords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A.goOtherActivityNoAnim(ArtActivity.this,EditCardActivity.class);
            }
        });
//        mwords.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/zfyx.ttf"));

        /**
         * 调整编辑界面图片的比例
         * */


        layoutParams = artPicsarea.getLayoutParams();
        ViewTreeObserver vto = artPicsarea.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                artPicsarea.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                layoutParams.width = (artPicsarea.getHeight() *9/16 );

            }
        });
        artPicsarea.setLayoutParams(layoutParams);
    }





    private void initImageView() {
//        ViewTreeObserver vto2 = imageView.getViewTreeObserver();
//        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//
////                ViewGroup.LayoutParams lp = imageView.getLayoutParams();
////                lp.height = layoutParams.height / 2;
////                imageView.setLayoutParams(lp);
////                ViewGroup.LayoutParams lp = imageView.getLayoutParams();
////                lp.width = ViewGroup.MarginLayoutParams.MATCH_PARENT;
////                imageView.setLayoutParams(lp);
//////                imageView.requestLayout();
//                Log.e("hhhhhhhh", imageView.getHeight() + "," + imageView.getWidth());
//            }
//        });
////
//        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.img));
    }





    @Override
    public void onFocusChange(View view, boolean b) {
    }
}
