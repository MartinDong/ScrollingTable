package com.example.kotlin.scrollingtable.type4;

/**
 * Created by xiaoyulaoshi on 2018/1/31.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class InterceptScrollContainer extends LinearLayout {
    private static final String TAG = "InterceptScrollContainer";

    public InterceptScrollContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public InterceptScrollContainer(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see android.view.ViewGroup#onInterceptTouchEvent(android.view.MotionEvent)
     * 拦截TouchEvent
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        Log.i(TAG,"onInterceptTouchEvent");
        return true;
        //return super.onInterceptTouchEvent(ev);
    }
}
