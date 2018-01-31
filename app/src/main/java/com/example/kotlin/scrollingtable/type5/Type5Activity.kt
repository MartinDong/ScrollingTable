package com.example.kotlin.scrollingtable.type5

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import com.example.kotlin.scrollingtable.R
import com.example.kotlin.scrollingtable.type2.model.Type2Model
import kotlinx.android.synthetic.main.activity_type5.*
import kotlinx.android.synthetic.main.item_layout_type5.*


class Type5Activity : AppCompatActivity() {
    private var TAG = Type5Activity::class.java.name


    private var mProductAdapter: RvType5Adapter? = null
    private var mProductDataList: MutableList<Type2Model>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type5)

        for (indexTitle in 0..5) {
            val titleView = LayoutInflater.from(this).inflate(R.layout.item_layout, null)
            titleView.findViewById<TextView>(R.id.tv_data).text = "表头》${indexTitle}"
            ll_item.addView(titleView)
        }

        layout_head.isFocusable = true
        layout_head.isClickable = true

        layout_head.setOnTouchListener(ListViewAndHeadViewTouchListener())
        rv_list_product.setOnTouchListener(ListViewAndHeadViewTouchListener())


        mProductAdapter = RvType5Adapter(hsv_list_right)
        mProductDataList = mutableListOf()
        for (index in 0..40) {
            val productModel = Type2Model()
            productModel.productName = "股票名称${index}"
            val priceList: MutableList<String> = mutableListOf()

            for (indexPrice in 0..5) {
                priceList.add("股票${index}价格${indexPrice}")
            }
            productModel.mPriceList = priceList

            mProductDataList!!.add(productModel)
        }
        mProductAdapter!!.setNewData(mProductDataList)
        rv_list_product.adapter = mProductAdapter

        //TODO 这里如果设置了点击事件会与设置到RecycleView的OnTouchListener冲突
        mProductAdapter?.setOnItemClickListener { adapter, view, position ->
            Log.d(TAG, "position>>" + position)
        }

    }

    internal inner class ListViewAndHeadViewTouchListener : View.OnTouchListener {

        override fun onTouch(arg0: View, event: MotionEvent): Boolean {
            // 当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
            val headScrollView = hsv_list_right
            headScrollView.onTouchEvent(event)
            return false
        }
    }
}
