package com.example.kotlin.scrollingtable.model

/**
 * Created by xiaoyulaoshi on 2018/1/30.
 */
class MainPriceModel {
    //当前条目是否选中
    var isPressed = false

    //产品名称
    var priceName: String? = null

    //当前产品价格属于哪个产品
    var productModel: MainProductModel? = null
}