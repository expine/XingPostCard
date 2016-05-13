package com.ashez.garfield.xingpostcard.Utils;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by Garfield on 5/2/16.
 * <p/>
 *
 */
public class BehaviorForEditLayout extends CoordinatorLayout.Behavior<View> {

    public BehaviorForEditLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof FloatingActionButton;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        int offset = dependency.getBottom() - 10;
        Animator animator = new Animator() {
            @Override
            public long getStartDelay() {
                return 0;
            }

            @Override
            public void setStartDelay(long l) {

            }

            @Override
            public Animator setDuration(long l) {
                return null;
            }

            @Override
            public long getDuration() {
                return 0;
            }

            @Override
            public void setInterpolator(TimeInterpolator timeInterpolator) {

            }

            @Override
            public boolean isRunning() {
                return false;
            }
        };

        return true;
    }


}
