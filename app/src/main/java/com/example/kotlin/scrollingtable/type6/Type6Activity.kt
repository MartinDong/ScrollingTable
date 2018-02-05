package com.example.kotlin.scrollingtable.type6


import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.AbsListView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.Toast
import com.example.kotlin.scrollingtable.R
import com.example.kotlin.scrollingtable.type4.model.ProductData
import com.example.kotlin.scrollingtable.type4.view.SyncHScrollView
import java.util.*

/**
 * 最完美实现，使用 ListView + HorizontalScrollView 实现
 * Created by xiaoyulaoshi on 2018/1/31.
 */
class Type6Activity : Activity() {

    //头部吸顶视图
    internal lateinit var mHeadStickyView: View
    //头部吸顶的右侧滑动视图
    internal lateinit var mHeadStickyHSView: SyncHScrollView
    //列表的头部视图
    internal lateinit var mHeadHeaderView: View
    //列表的头部视图的右侧滑动视图
    internal lateinit var mHeadHeaderHSView: SyncHScrollView
    //列表视图
    internal lateinit var mListView: ListView
    //数据适配器
    internal lateinit var type4Adapter: Type6Adapter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type6)

        //列表的头部视图
        mHeadHeaderView = View.inflate(this, R.layout.item_layout_type4, null)
        mHeadHeaderHSView = mHeadHeaderView.findViewById(R.id.horizontalScrollView1)
        mHeadHeaderView.isClickable = true

        //头部吸顶视图
        mHeadStickyView = findViewById(R.id.head)
        mHeadStickyHSView = findViewById(R.id.horizontalScrollView1)
        mHeadStickyHSView.AddOnScrollChangedListener(Type6Adapter.OnScrollChangedListenerImp(mHeadHeaderHSView))

        //TODO 划重点：这里需要从传入的列表头拿到里面的右侧滑动控件
        mHeadHeaderView.setOnTouchListener(ListViewAndHeadViewTouchListener())

        mListView = findViewById(R.id.lv_produce)
        mListView.addHeaderView(mHeadHeaderView)
        mListView.setOnTouchListener(ListViewAndHeadViewTouchListener())

        // 创建当前用于显示视图的数据
        val currentData = ArrayList<ProductData>()
        for (i in 0..49) {
            val data = ProductData()
            data.str1 = "股票>" + i
            data.str2 = "价格>1"
            data.str3 = "价格>2"
            data.str4 = "价格>3"
            data.str5 = "价格>4"
            data.str6 = "价格>5"
            data.str7 = "价格>6"
            data.str8 = "价格>7"
            currentData.add(data)
        }


        type4Adapter = Type6Adapter(this, R.layout.item_layout_type4, currentData, mHeadHeaderHSView)
        mListView.adapter = type4Adapter
        // OnClick监听
        mListView.onItemClickListener = OnItemClickListener { arg0, arg1, arg2, arg3 ->
            Log.i("Type4Activity ListView", "onItemClick Event")
            Toast.makeText(this@Type6Activity, "点了第" + arg2 + "个",
                    Toast.LENGTH_SHORT).show()
        }


        mListView.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                if (firstVisibleItem >= 1) {
                    mHeadStickyView.visibility = View.VISIBLE
                } else {
                    mHeadStickyView.visibility = View.GONE
                }
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

            }
        })
    }

    /**
     * TODO 划重点：用来将头部和列表上面的触摸事件都分发给头部的滑动控件
     */
    internal inner class ListViewAndHeadViewTouchListener : View.OnTouchListener {

        override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
            // 当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
            mHeadHeaderHSView.onTouchEvent(arg1)
            mHeadStickyHSView.onTouchEvent(arg1)
            return false
        }
    }

}
