package com.example.kotlin.scrollingtable

import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlin.scrollingtable.model.ProductModel

/**
 * 每一行的股票信息，第一条是股票名称，之后的是价格信息
 * Created by kotlin on 18-1-29.
 */
class RvProductAdapter : BaseQuickAdapter<ProductModel, BaseViewHolder>(R.layout.item_product_layout) {
    private var TAG = RvProductAdapter::class.java.name

    override fun convert(helper: BaseViewHolder, item: ProductModel) {
        //当前的条目位置信息
        val productPosition = helper.adapterPosition

        helper.itemView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    setOnItemClick(helper.itemView, productPosition)
                }
            }
            false
        }

        helper.setText(R.id.tv_product_name, item.productName)

        val rightAdapter = RvAdapter()
        rightAdapter.setNewData(item.mPriceList)
        val itemRecyclerView = helper.getView<RecyclerView>(R.id.rv_list_right)

        itemRecyclerView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    helper.itemView.isPressed = true
                }
                MotionEvent.ACTION_UP -> {
                    helper.itemView.isPressed = false
                    setOnItemClick(helper.itemView, productPosition)
                }
            }
            false
        }

        itemRecyclerView.adapter = rightAdapter
        itemRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (recyclerView.scrollState != RecyclerView.SCROLL_STATE_IDLE) {
                    if (productPosition > 0) {
                        //val nextItem = helper.getView(productPosition)

                    }
                    //rv_list_right.scrollBy(dx, dy)
                }
            }
        })

    }
}
