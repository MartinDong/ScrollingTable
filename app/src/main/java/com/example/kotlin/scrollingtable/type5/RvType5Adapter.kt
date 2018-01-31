package com.example.kotlin.scrollingtable.type5

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlin.scrollingtable.R
import com.example.kotlin.scrollingtable.type2.model.Type2Model
import com.example.kotlin.scrollingtable.type5.view.SyncRecyclerView

/**
 * 每一行的股票信息，第一条是股票名称，之后的是价格信息
 * Created by kotlin on 18-1-29.
 */
class RvType5Adapter(
        //列表头部需要进行横向滑动关联
        var mHeadSyncRecyclerView: SyncRecyclerView
) : BaseQuickAdapter<Type2Model, BaseViewHolder>(R.layout.item_layout_type5) {
    private var TAG = RvType5Adapter::class.java.name


    override fun convert(helper: BaseViewHolder, item: Type2Model) {
        //当前的条目位置信息
        val productPosition = helper.adapterPosition

        helper.setText(R.id.tv_product_name, item.productName)

        //右侧横向列表的数据适配器
        val rightAdapter = RvType5RightAdapter()
        rightAdapter.setNewData(item.mPriceList)

        //右侧横向列表
        val itemRecyclerView = helper.getView<SyncRecyclerView>(R.id.rv_list_right)
        //添加数据绑定
        itemRecyclerView.adapter = rightAdapter

        //添加滑动观察者
        mHeadSyncRecyclerView.addOnScrollChangeListener(object : SyncRecyclerView.OnScrollChangeListener {
            override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
                itemRecyclerView.scrollBy(l, t)
            }
        })

    }
}
