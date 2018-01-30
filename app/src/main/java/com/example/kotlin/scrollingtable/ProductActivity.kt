package com.example.kotlin.scrollingtable

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kotlin.scrollingtable.model.ProductModel
import kotlinx.android.synthetic.main.activity_main.*

class ProductActivity : AppCompatActivity() {

    private var TAG= ProductActivity::class.java.name

    private var mProductAdapter: RvProductAdapter? = null
    private var mProductDataList: MutableList<ProductModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mProductAdapter = RvProductAdapter()
        mProductDataList = mutableListOf()
        for (index in 1..40) {
            val productModel = ProductModel()
            productModel.productName = "股票名称${index}"
            val priceList: MutableList<String> = mutableListOf()

            for (indexPrice in 1..5) {
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
