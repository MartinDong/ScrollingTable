package com.example.kotlin.scrollingtable.type3

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.kotlin.scrollingtable.R
import com.example.kotlin.scrollingtable.type3.model.Type3PriceModel
import com.example.kotlin.scrollingtable.type3.model.Type3ProductModel
import kotlinx.android.synthetic.main.activity_type1.*

class Type3Activity : AppCompatActivity() {
    private var TAG = Type3Activity::class.java.name

    private var mLeftAdapter: RvType3LeftAdapter? = null

    private var mRightAdapter: RvType3RightAdapter? = null

    private var mDataList: MutableList<Type3ProductModel> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type3)

        mLeftAdapter = RvType3LeftAdapter()
        mRightAdapter = RvType3RightAdapter()


        for (produceIndex in 0..40) {
            val productModel = Type3ProductModel()
            productModel.productName = "股票名称$produceIndex"

            for (priceIndex in 0..4) {
                val mainPriceModel = Type3PriceModel()
                mainPriceModel.priceName = "股票${produceIndex}价格-${priceIndex}"
                productModel.mRightDataList.add(mainPriceModel)
            }

            mDataList.add(productModel)
        }


        mLeftAdapter!!.setNewData(mDataList)
        rv_list_left.adapter = mLeftAdapter

        mRightAdapter!!.setNewData(mDataList)
        rv_list_right.adapter = mRightAdapter

        mLeftAdapter!!.rvMainRightAdapter = mRightAdapter
        mRightAdapter!!.rvRvMainLeftAdapter = mLeftAdapter

        //注册条目点击监听
        mLeftAdapter?.setOnItemClickListener { adapter, view, position ->
            Log.d(TAG, "mLeftAdapter click position >> " + position)
        }
        mRightAdapter?.setOnItemClickListener { adapter, view, position ->
            Log.d(TAG, "mRightAdapter click position >> " + position)
        }


        //TODO 注意喽，要划重点了，要考的
        //TODO 左侧的RecyclerView与右侧RecyclerView垂直方向的滑动相互监听，实现联动效果
        rv_list_left.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (recyclerView.scrollState != RecyclerView.SCROLL_STATE_IDLE) {
                    rv_list_right.scrollBy(dx, dy)
                }
            }
        })
        rv_list_right.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (recyclerView.scrollState != RecyclerView.SCROLL_STATE_IDLE) {
                    rv_list_left.scrollBy(dx, dy)
                }
            }
        })


    }
}
