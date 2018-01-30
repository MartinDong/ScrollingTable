package com.example.kotlin.scrollingtable.type3

import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlin.scrollingtable.R
import com.example.kotlin.scrollingtable.type3.model.Type3ProductModel

/**
 * 每一行的股票信息，第一条是股票名称，之后的是价格信息
 * Created by kotlin on 18-1-29.
 */
class RvType3RightAdapter : BaseQuickAdapter<Type3ProductModel, BaseViewHolder>(R.layout.item_layout_type3) {
    private var TAG = RvType3RightAdapter::class.java.name

    var rvRvMainLeftAdapter: RvType3LeftAdapter? = null

    override fun convert(helper: BaseViewHolder, item: Type3ProductModel) {
        //当前的条目位置信息
        val productPosition = helper.adapterPosition

        val ll_item = helper.getView<LinearLayout>(R.id.ll_item)

        item.mRightDataList.forEach {
            val itemView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, null)
            itemView.findViewById<TextView>(R.id.tv_data).text = it.priceName
            ll_item.addView(itemView)
        }

    }
}
