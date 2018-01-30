package com.example.kotlin.scrollingtable.type1

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlin.scrollingtable.R
import com.example.kotlin.scrollingtable.type1.model.Type1PriceModel

/**
 * 每一行的股票信息，第一条是股票名称，之后的是价格信息
 * Created by kotlin on 18-1-29.
 */
class RvType1RightAdapter : BaseQuickAdapter<Type1PriceModel, BaseViewHolder>(R.layout.item_layout_type1) {
    private var TAG = RvType1RightAdapter::class.java.name

    var rvRvMainLeftAdapter: RvType1LeftAdapter? = null

    override fun convert(helper: BaseViewHolder, item: Type1PriceModel) {
        //当前的条目位置信息
        val productPosition = helper.adapterPosition

        helper.setText(R.id.tv_data, item.priceName)

    }
}
