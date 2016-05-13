package com.ashez.garfield.xingpostcard.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ashez.garfield.xingpostcard.R;
import com.ashez.garfield.xingpostcard.Utils.Ruler;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Garfield on 5/14/16.
 */
public class ResultActivity extends Activity {

    @Bind(R.id.result_iv)
    ImageView resultIv;
    private int windowWidth;
    private int windowHeight;
    private Bitmap pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);


        windowWidth = Ruler.getWindowWidth(this);//单位为像素
        windowHeight = Ruler.getWindowHeight(this);//单位为像素

        byte[] bis = getIntent().getByteArrayExtra("bitmap");
        if (bis != null) {
            pic = BitmapFactory.decodeByteArray(bis, 0, bis.length);
        }

        Bitmap bmp = Bitmap.createBitmap(pic.getWidth(), pic.getWidth() * 16 / 9, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setTextSize(Ruler.dip2px(this, 14));
        paint.setAntiAlias(true);

        canvas.drawBitmap(pic, 0, 0, paint);
        canvas.drawText(getIntent().getStringExtra("mwords"), Ruler.dip2px(this, 30), Ruler.dip2px(this, 45) + pic.getHeight(), paint);

        resultIv.setImageBitmap(bmp);

    }
}
