package com.example.kotlin.scrollingtable.type5.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout


/**
 * 拦截触摸事件的容器
 * Created by xiaoyulaoshi on 2018/1/31.
 */

class InterceptScrollLinerLayout : LinearLayout {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context) : super(context) {}

    /**
     * 拦截TouchEvent
     * @see android.view.ViewGroup#onInterceptTouchEvent(android.view.MotionEvent)
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        Log.i(TAG, "onInterceptTouchEvent" + ev)
        return true
    }

    companion object {
        private val TAG = InterceptScrollLinerLayout::class.java.name
    }
}
