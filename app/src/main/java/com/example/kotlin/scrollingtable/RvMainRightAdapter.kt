package com.example.kotlin.scrollingtable

import android.view.MotionEvent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlin.scrollingtable.model.MainPriceModel

/**
 * 每一行的股票信息，第一条是股票名称，之后的是价格信息
 * Created by kotlin on 18-1-29.
 */
class RvMainRightAdapter : BaseQuickAdapter<MainPriceModel, BaseViewHolder>(R.layout.item_layout_main) {
    private var TAG = RvMainRightAdapter::class.java.name

    var rvRvMainLeftAdapter: RvMainLeftAdapter? = null

    override fun convert(helper: BaseViewHolder, item: MainPriceModel) {
        //当前的条目位置信息
        val productPosition = helper.adapterPosition

        helper.setText(R.id.tv_data, item.priceName)

        helper.itemView.isPressed = item.productModel?.isPressed!!

        helper.itemView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    //标记当前的触摸条目变色
                    helper.itemView.isPressed = true
                    data[productPosition]?.productModel?.isPressed = true
                }
                MotionEvent.ACTION_UP -> {
                    //标记当前的触摸条目变色
                    helper.itemView.isPressed = false
                    data[productPosition]?.productModel?.isPressed = false
                }
            }
            rvRvMainLeftAdapter?.notifyDataSetChanged()
            false
        }
    }
}
