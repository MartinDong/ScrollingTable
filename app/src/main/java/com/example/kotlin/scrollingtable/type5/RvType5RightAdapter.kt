package com.example.kotlin.scrollingtable.type5

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlin.scrollingtable.R

/**
 * Created by kotlin on 18-1-29.
 */
class RvType5RightAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_layout_type1) {
    override fun convert(helper: BaseViewHolder, item: String) {
        helper.setText(R.id.tv_data, item)
    }
}
