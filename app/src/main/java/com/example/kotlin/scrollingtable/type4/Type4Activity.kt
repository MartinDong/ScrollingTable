package com.example.kotlin.scrollingtable.type4


import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.RelativeLayout
import android.widget.Toast
import com.example.kotlin.scrollingtable.R
import com.example.kotlin.scrollingtable.type4.model.Data
import java.util.*

/**
 * 最完美实现，使用 ListView + HorizontalScrollView 实现
 * Created by xiaoyulaoshi on 2018/1/31.
 */
class Type4Activity : Activity() {
    internal lateinit var mListView1: ListView
    internal lateinit var mHead: RelativeLayout
    internal lateinit var type4Adapter: Type4Adapter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type4)

        mHead = findViewById(R.id.head)
        mHead.isFocusable = true
        mHead.isClickable = true
        mHead.setOnTouchListener(ListViewAndHeadViewTouchLinstener())


        mListView1 = findViewById(R.id.listView1)
        mListView1.setOnTouchListener(ListViewAndHeadViewTouchLinstener())

        // 创建当前用于显示视图的数据
        val currentData = ArrayList<Data>()
        for (i in 0..49) {
            val data = Data()
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


        type4Adapter = Type4Adapter(this, R.layout.item_layout_type4, currentData, mHead)
        mListView1.adapter = type4Adapter
        // OnClick监听
        mListView1.onItemClickListener = OnItemClickListener { arg0, arg1, arg2, arg3 ->
            Log.i("Type4Activity ListView", "onItemClick Event")
            Toast.makeText(this@Type4Activity, "点了第" + arg2 + "个",
                    Toast.LENGTH_SHORT).show()
        }

    }

    internal inner class ListViewAndHeadViewTouchLinstener : View.OnTouchListener {

        override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
            // 当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
            val headSrcrollView = mHead.findViewById<HorizontalScrollView>(R.id.horizontalScrollView1)
            headSrcrollView.onTouchEvent(arg1)
            return false
        }
    }

}
