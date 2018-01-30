package com.example.kotlin.scrollingtable.type2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kotlin.scrollingtable.R
import com.example.kotlin.scrollingtable.type2.model.Type2Model
import kotlinx.android.synthetic.main.activity_type1.*

class Type2Activity : AppCompatActivity() {

    private var TAG = Type2Activity::class.java.name

    private var mProductAdapter: RvType2Adapter? = null
    private var mProductDataList: MutableList<Type2Model>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type1)

        mProductAdapter = RvType2Adapter()
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
        rv_list_left.adapter = mProductAdapter

        mProductAdapter?.setOnItemClickListener { adapter, view, position ->
            Log.d(TAG, "position>>" + position)
        }
    }
}
