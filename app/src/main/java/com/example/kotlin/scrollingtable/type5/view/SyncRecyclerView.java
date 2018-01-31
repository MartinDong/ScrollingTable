package com.example.kotlin.scrollingtable.type5.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 可以实现关联滑动的RecyclerView,实现方式为观察者机制
 * Created by xiaoyulaoshi on 2018/1/31.
 */

public class SyncRecyclerView extends RecyclerView {

    private final String TAG = SyncRecyclerView.class.getName();

    //观察者
    private SyncRecyclerViewObserver mSyncRecyclerViewObserver = new SyncRecyclerViewObserver();


    public SyncRecyclerView(Context context) {
        super(context);
    }

    public SyncRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SyncRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return super.onTouchEvent(e);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        Log.i(TAG, "onScrolled >> dx=" + dx + "\tdy=" + dy);
        //将发生的滑动改变通知到观察者中的每一个滑动监听
        if (mSyncRecyclerViewObserver != null) {
            mSyncRecyclerViewObserver.notifyOnScrollChange(dx, dy);
        }
        super.onScrolled(dx, dy);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    /**
     * 向观察者中添加 当前控件的滑动事件监听
     */
    public void addOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        mSyncRecyclerViewObserver.addOnScrollChangeListener(onScrollChangeListener);
    }


    /**
     * 移除观察者中 当前控件的滑动事件监听
     */
    public void removeOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        mSyncRecyclerViewObserver.removeOnScrollChangeListener(onScrollChangeListener);
    }


    /**
     * 自定义滑动监听
     */
    public interface OnScrollChangeListener {
        void onScrollChanged(int dx, int dy);
    }

    /**
     * 滑动观察者，用来多个列表关联滑动的实现
     */
    public static class SyncRecyclerViewObserver {
        //创建关联监听的集合，因为每一个列表都会设置监听器
        List<OnScrollChangeListener> mList;

        public SyncRecyclerViewObserver() {
            super();
            mList = new ArrayList<>();
        }

        /**
         * 添加滑动监听器
         *
         * @param onScrollChangeListener 滑动监听器
         */
        public void addOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
            mList.add(onScrollChangeListener);
        }

        /**
         * 移除滑动监听器
         *
         * @param onScrollChangeListener 要删除的滑动监听器
         */
        public void removeOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
            mList.remove(onScrollChangeListener);
        }

        /**
         * 广播滑动改变，通知到每一个滑动监听器
         */
        public void notifyOnScrollChange(int dx, int dy) {
            if (mList == null || mList.size() == 0) {
                return;
            }

            //循环通知每一个滑动监听器
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i) != null) {
                    mList.get(i).onScrollChanged(dx, dy);
                }
            }
        }

    }
}
