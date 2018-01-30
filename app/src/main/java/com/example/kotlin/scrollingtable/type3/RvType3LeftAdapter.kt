package com.example.kotlin.scrollingtable.type3

import android.support.v7.widget.LinearLayoutManager
import android.view.MotionEvent
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlin.scrollingtable.R
import com.example.kotlin.scrollingtable.type3.model.Type3ProductModel

/**
 * 每一行的股票信息，第一条是股票名称，之后的是价格信息
 * Created by kotlin on 18-1-29.
 */
class RvType3LeftAdapter : BaseQuickAdapter<Type3ProductModel, BaseViewHolder>(R.layout.item_layout_type1) {
    private var TAG = RvType3LeftAdapter::class.java.name

    var rvRightLinearLayoutManager: LinearLayoutManager? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun convert(helper: BaseViewHolder, item: Type3ProductModel) {
        //当前的条目位置信息
        val productPosition = helper.adapterPosition

        helper.setText(R.id.tv_data, item.productName)

        helper.itemView.setOnTouchListener { view, event ->
            val ll_item = rvRightLinearLayoutManager?.findViewByPosition(productPosition)

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    helper.itemView.isPressed = true
                    ll_item?.isPressed = true
                }
                MotionEvent.ACTION_UP -> {
                    helper.itemView.isPressed = false
                    ll_item?.isPressed = false
                }
                MotionEvent.ACTION_MOVE -> {

                }
            }
            false
        }
    }
}
