package com.example.kotlin.scrollingtable.type4.view;

/**
 * Created by xiaoyulaoshi on 2018/1/31.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class InterceptScrollContainer extends LinearLayout {
    private static final String TAG = InterceptScrollContainer.class.getName();

    public InterceptScrollContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptScrollContainer(Context context) {
        super(context);
    }

    /* (non-Javadoc)
     * @see android.view.ViewGroup#onInterceptTouchEvent(android.view.MotionEvent)
     * 拦截TouchEvent
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent" + ev);
        return true;
    }
}
