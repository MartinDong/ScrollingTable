package com.example.kotlin.scrollingtable.type2.model

/**
 * Created by xiaoyulaoshi on 2018/1/30.
 */
class Type2Model {
    //当前条目是否选中
    var isPressed = false
    //产品名称
    var productName: String? = null
    //产品价格数据集合
    var mPriceList: MutableList<String>? = null
}