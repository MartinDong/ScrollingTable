package com.example.kotlin.scrollingtable.type5

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.kotlin.scrollingtable.R
import com.example.kotlin.scrollingtable.type2.model.Type2Model
import kotlinx.android.synthetic.main.activity_type5.*
import kotlinx.android.synthetic.main.item_layout_type5_head.*


class Type5Activity : AppCompatActivity() {
    private var TAG = Type5Activity::class.java.name


    private var mProductAdapter: RvType5Adapter? = null
    private var mProductDataList: MutableList<Type2Model>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type5)

        val rightTitleList: MutableList<String> = mutableListOf()
        for (indexTitle in 0..5) {
            rightTitleList.add("表头》${indexTitle}")
        }

        val headRightAdapter = RvType5RightAdapter()
        headRightAdapter.setNewData(rightTitleList)
        rv_list_right.adapter = headRightAdapter

        mProductAdapter = RvType5Adapter(rv_list_right)
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

        mProductAdapter?.setOnItemClickListener { adapter, view, position ->
            Log.d(TAG, "position>>" + position)
        }


        rv_list_product.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                /*
                new State
                0（SCROLL_STATE_IDLE）表示recyclerview是不动的（The RecyclerView is not currently scrolling.）
                1（SCROLL_STATE_DRAGGING）表示recyclerview正在被拖拽（The RecyclerView is currently being dragged by outside input such as user touch input.）
                2（SCROLL_STATE_SETTLING）表示recyclerview正在惯性下滚动（The RecyclerView is currently animating to a final position while not under outside control.）
                 */
                when (newState) {
                    0 -> println("recyclerview已经停止滚动")
                    1 -> println("recyclerview正在被拖拽")
                    2 -> println("recyclerview正在依靠惯性滚动")
                }
            }
        })
    }
}
