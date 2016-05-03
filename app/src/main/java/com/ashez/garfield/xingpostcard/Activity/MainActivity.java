package com.ashez.garfield.xingpostcard.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ashez.garfield.xingpostcard.R;
import com.ashez.xingpostcard.adapter.CoverFlow;
import com.ashez.xingpostcard.adapter.CoverFlowSampleAdapter;

public class MainActivity extends AppCompatActivity {

    private CoverFlow fancyCoverFlow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fancyCoverFlow = (CoverFlow) this.findViewById(R.id.fancyCoverFlow);

        this.fancyCoverFlow.setAdapter(new CoverFlowSampleAdapter());
        this.fancyCoverFlow.setUnselectedAlpha(1.0f);
        this.fancyCoverFlow.setUnselectedSaturation(0.0f);
        this.fancyCoverFlow.setUnselectedScale(0.5f);
        this.fancyCoverFlow.setSpacing(50);
        this.fancyCoverFlow.setMaxRotation(0);
        this.fancyCoverFlow.setScaleDownGravity(0.2f);
        this.fancyCoverFlow.setActionDistance(CoverFlow.ACTION_DISTANCE_AUTO);
    }
}
