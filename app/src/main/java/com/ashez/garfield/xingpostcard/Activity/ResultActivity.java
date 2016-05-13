package com.ashez.garfield.xingpostcard.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.ashez.garfield.xingpostcard.R;
import com.ashez.garfield.xingpostcard.Utils.Ruler;

/**
 * Created by Garfield on 5/14/16.
 */
public class ResultActivity extends Activity{

    private int windowWidth;
    private int windowHeight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        windowWidth = Ruler.getWindowWidth(this);//单位为像素
        windowHeight = Ruler.getWindowHeight(this);//单位为像素


    }
}
