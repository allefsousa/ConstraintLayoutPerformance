package com.developer.allef.layouttest;

import android.content.Context;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
@RunWith(AndroidJUnit4.class)
public class ConstraintLayoutTest {
    @Test
    public void test() {
        final long constraintLayoutTime = getLayoutTime(R.layout.constraint_layout);
        final long linearLayoutTime = getLayoutTime(R.layout.linear_layout);
        final long relativeLayoutTime = getLayoutTime(R.layout.relative_layout);

        Log.i("teste", "constraint : " + constraintLayoutTime);
        Log.i("teste", "linear : " + linearLayoutTime);
        Log.i("teste", "relative : " + relativeLayoutTime);

    }
    private long getLayoutTime(int layoutRes) {
        final Context targetContext = getInstrumentation().getTargetContext();
        final LayoutInflater layoutInflater = LayoutInflater.from(targetContext);

        final long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            final View view = layoutInflater.inflate(layoutRes, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(0, 0));

            view.measure(View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            final int measuredHeight = view.getMeasuredHeight();
            final int measuredWidth = view.getMeasuredWidth();

            view.layout(0, 0, measuredWidth, measuredHeight);
        }
        return System.currentTimeMillis() - startTime;
    }


}
