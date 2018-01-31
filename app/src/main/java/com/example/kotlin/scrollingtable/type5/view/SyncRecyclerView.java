package com.example.kotlin.scrollingtable.type5.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * 可以实现关联滑动的RecyclerView,实现方式为观察者机制
 * Created by xiaoyulaoshi on 2018/1/31.
 */

public class SyncRecyclerView extends RecyclerView {

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
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        //将发生的滑动改变通知到观察者中的每一个滑动监听
        if (mSyncRecyclerViewObserver != null) {
            mSyncRecyclerViewObserver.notifyOnScrollChange(l, t, oldl, oldt);
        }
        super.onScrollChanged(l, t, oldl, oldt);
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
        void onScrollChanged(int l, int t, int oldl, int oldt);
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
         *
         * @param l    Current horizontal scroll origin. 当前水平滑动源点
         * @param t    Current vertical scroll origin.   当前垂直滑动源点
         * @param oldl Previous horizontal scroll origin.之前的水平滑动源点
         * @param oldt Previous vertical scroll origin.  之前的垂直滑动源点
         */
        public void notifyOnScrollChange(int l, int t, int oldl, int oldt) {
            if (mList == null || mList.size() == 0) {
                return;
            }

            //循环通知每一个滑动监听器
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i) != null) {
                    mList.get(i).onScrollChanged(l, t, oldl, oldt);
                }
            }
        }

    }
}
