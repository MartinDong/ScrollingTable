package com.example.kotlin.scrollingtable

import android.view.MotionEvent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlin.scrollingtable.model.MainProductModel

/**
 * 每一行的股票信息，第一条是股票名称，之后的是价格信息
 * Created by kotlin on 18-1-29.
 */
class RvMainLeftAdapter : BaseQuickAdapter<MainProductModel, BaseViewHolder>(R.layout.item_layout_main) {
    private var TAG = RvMainLeftAdapter::class.java.name

    var rvMainRightAdapter: RvMainRightAdapter? = null


    override fun convert(helper: BaseViewHolder, item: MainProductModel) {
        //当前的条目位置信息
        val productPosition = helper.adapterPosition

        helper.setText(R.id.tv_data, item.productName)

        helper.itemView.isPressed = item.isPressed

        helper.itemView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    helper.itemView.isPressed = true
                    data[productPosition].isPressed = true
                }
                MotionEvent.ACTION_UP -> {
                    helper.itemView.isPressed = false
                    data[productPosition].isPressed = false
                }
            }
            rvMainRightAdapter?.notifyDataSetChanged()
            false
        }
    }
}
