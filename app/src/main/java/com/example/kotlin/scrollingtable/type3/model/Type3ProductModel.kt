package com.example.kotlin.scrollingtable.type3.model

/**
 * Created by xiaoyulaoshi on 2018/1/30.
 */
class Type3ProductModel {
    //当前条目是否选中
    var isPressed = false
    //产品名称
    var productName: String = ""
    //产品内部的价格列表
    var mRightDataList: MutableList<Type3PriceModel> = mutableListOf()
}