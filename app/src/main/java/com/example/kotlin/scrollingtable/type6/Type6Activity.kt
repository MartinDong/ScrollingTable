package com.example.kotlin.scrollingtable.type6


import android.app.Activity
import android.os.Bundle
import android.util.Log
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
    //列表视图
    internal lateinit var mListView: ListView
    //数据适配器
    internal lateinit var type4Adapter: Type6Adapter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type6)

        //头部吸顶视图
        mHeadStickyView = findViewById(R.id.head)
        mHeadStickyHSView = findViewById(R.id.horizontalScrollView1)

        //TODO 划重点：这里需要从传入的列表头拿到里面的右侧滑动控件

        mListView = findViewById(R.id.lv_produce)

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

        val data = ProductData()
        data.typeItem = 1
        currentData.add(0, data)
        currentData.add(1, data)

        val dataHead = ProductData()
        dataHead.typeItem = 2
        currentData.add(2, dataHead)

        currentData.add(10, data)
        currentData.add(11, data)


        type4Adapter = Type6Adapter(this, currentData, mListView, mHeadStickyHSView)
        mListView.adapter = type4Adapter
        // OnClick监听
        mListView.onItemClickListener = OnItemClickListener { arg0, arg1, arg2, arg3 ->
            Log.i("Type4Activity ListView", "onItemClick Event")
            Toast.makeText(this@Type6Activity, "点了第" + arg2 + "个",
                    Toast.LENGTH_SHORT).show()
        }


        mListView.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                if (currentData[firstVisibleItem].typeItem == 0) {
                    mHeadStickyView.visibility = View.VISIBLE
                } else {
                    mHeadStickyView.visibility = View.GONE
                }
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

            }
        })
    }


}
