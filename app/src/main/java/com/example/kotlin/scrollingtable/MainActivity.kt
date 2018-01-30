package com.example.kotlin.scrollingtable

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mLeftAdapter: RvAdapter? = null
    private var mLeftDataList: MutableList<String>? = null

    private var mRightAdapter: RvAdapter? = null
    private var mRightDataList: MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mLeftAdapter = RvAdapter()
        mLeftDataList = mutableListOf()
        for (index in 1..40) {
            mLeftDataList!!.add("股票名称$index")
        }
        mLeftAdapter!!.setNewData(mLeftDataList)
        rv_list_left.adapter = mLeftAdapter


        mRightAdapter = RvAdapter()
        mRightDataList = mutableListOf()
        for (produceIndex in 1..40) {
            for (priceIndex in 1..5) {
                mRightDataList!!.add("股票${produceIndex}价格-${priceIndex}")
            }
        }
        mRightAdapter!!.setNewData(mRightDataList)
        rv_list_right.adapter = mRightAdapter

        //注册条目点击监听
        mLeftAdapter?.setOnItemClickListener { adapter, view, position ->

        }
        mRightAdapter?.setOnItemClickListener { adapter, view, position ->

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
