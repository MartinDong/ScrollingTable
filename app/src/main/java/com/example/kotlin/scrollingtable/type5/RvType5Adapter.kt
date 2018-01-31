package com.example.kotlin.scrollingtable.type5

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlin.scrollingtable.R
import com.example.kotlin.scrollingtable.type2.model.Type2Model
import com.example.kotlin.scrollingtable.type5.view.SyncHScrollView

/**
 * 每一行的股票信息，第一条是股票名称，之后的是价格信息
 * Created by kotlin on 18-1-29.
 */
class RvType5Adapter(
        //列表头部需要进行横向滑动关联
        var mHeadSyncRecyclerView: SyncHScrollView
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
        val itemRecyclerView = helper.getView<SyncHScrollView>(R.id.hsv_list_right)

        //添加滑动观察者
        mHeadSyncRecyclerView.AddOnScrollChangedListener(OnScrollChangedListenerImp(itemRecyclerView))

        //TODO 这里如果设置了点击事件会与设置到RecycleView的OnTouchListener冲突
//        helper.itemView.setOnClickListener {
//
//        }

    }

    internal inner class OnScrollChangedListenerImp(var mScrollViewArg: SyncHScrollView) :
            SyncHScrollView.OnScrollChangedListener {

        override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
            mScrollViewArg.smoothScrollTo(l, t)
        }
    }
}
