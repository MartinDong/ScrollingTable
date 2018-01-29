package com.example.kotlin.scrollingtable

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mGridAdapter: RvGridAdapter? = null

    private var mDataList: MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mGridAdapter = RvGridAdapter()
        mDataList = mutableListOf()

        for (index in 0..100) {
            mDataList!!.add("数据${index}")
        }

        mGridAdapter!!.setNewData(mDataList)

        rv_list.adapter = mGridAdapter

    }
}
