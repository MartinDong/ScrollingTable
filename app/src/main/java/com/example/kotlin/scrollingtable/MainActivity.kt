package com.example.kotlin.scrollingtable

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.kotlin.scrollingtable.model.MainPriceModel
import com.example.kotlin.scrollingtable.model.MainProductModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var TAG = MainActivity::class.java.name

    private var mLeftAdapter: RvMainLeftAdapter? = null
    private var mLeftDataList: MutableList<MainProductModel> = mutableListOf()

    private var mRightAdapter: RvMainRightAdapter? = null
    private var mRightDataList: MutableList<MainPriceModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mLeftAdapter = RvMainLeftAdapter()
        mRightAdapter = RvMainRightAdapter()


        for (produceIndex in 0..40) {
            val productModel = MainProductModel()
            productModel.producrId = produceIndex
            productModel.productName = "股票名称$produceIndex"

            for (priceIndex in 0..4) {
                val mainPriceModel = MainPriceModel()
                mainPriceModel.priceName = "股票${produceIndex}价格-${priceIndex}"
                mainPriceModel.productModel = productModel
                mRightDataList.add(mainPriceModel)
            }

            mLeftDataList.add(productModel)
        }


        mLeftAdapter!!.setNewData(mLeftDataList)
        rv_list_left.adapter = mLeftAdapter


        mRightAdapter!!.setNewData(mRightDataList)
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
