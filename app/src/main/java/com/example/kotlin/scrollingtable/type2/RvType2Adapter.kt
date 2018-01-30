package com.example.kotlin.scrollingtable.type2

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MotionEvent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlin.scrollingtable.R
import com.example.kotlin.scrollingtable.type2.model.Type2Model

/**
 * 每一行的股票信息，第一条是股票名称，之后的是价格信息
 * Created by kotlin on 18-1-29.
 */
class RvType2Adapter : BaseQuickAdapter<Type2Model, BaseViewHolder>(R.layout.item_product_layout) {
    private var TAG = RvType2Adapter::class.java.name

    private var onScrollListener: RecyclerView.OnScrollListener? = null


    override fun convert(helper: BaseViewHolder, item: Type2Model) {
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

        val rightAdapter = RvType2RightAdapter()
        rightAdapter.setNewData(item.mPriceList)
        val itemRecyclerView = helper.getView<RecyclerView>(R.id.rv_list_right)

        itemRecyclerView.setOnTouchListener { v, event ->
            var oldX = 0f
            var oldY = 0f
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    oldX = event.x
                    oldY = event.y

                    helper.itemView.isPressed = true
                }
                MotionEvent.ACTION_UP -> {
                    helper.itemView.isPressed = false
                    setOnItemClick(helper.itemView, productPosition)
                }
                MotionEvent.ACTION_MOVE -> {
                    var newX = event.x
                    var newY = event.y

                    var moveX = Math.abs(newX - oldX)
                    var moveY = Math.abs(newY - oldY)

                    Log.e(TAG, "moveX >> " + moveX)
                    Log.e(TAG, "moveY >> " + moveY)

                    if (moveY > v.height) {
                        helper.itemView.isPressed = false
                    }
                }
            }
            false
        }

        if (null == onScrollListener) {
            onScrollListener = object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (recyclerView.scrollState != RecyclerView.SCROLL_STATE_IDLE) {
                        itemRecyclerView.scrollBy(dx, dy)
                    }
                }
            }
        }

        itemRecyclerView.adapter = rightAdapter
        itemRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (recyclerView.scrollState != RecyclerView.SCROLL_STATE_IDLE) {
                    onScrollListener?.onScrolled(recyclerView, dx, dy)
                }
            }
        })

    }
}
