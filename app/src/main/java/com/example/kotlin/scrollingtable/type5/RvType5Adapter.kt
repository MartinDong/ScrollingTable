package com.example.kotlin.scrollingtable.type5

import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
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
        val ll_item = helper.getView<LinearLayout>(R.id.ll_item)
        ll_item.removeAllViews()

        item.mPriceList?.forEach {
            val itemView = getItemView(R.layout.item_layout, null)
            itemView.findViewById<TextView>(R.id.tv_data).text = it
            ll_item.addView(itemView)
        }

        //右侧横向列表
        val itemSyncHScrollView = helper.getView<SyncHScrollView>(R.id.hsv_list_right)

        //添加滑动观察者
        mHeadSyncRecyclerView.AddOnScrollChangedListener(OnScrollChangedListenerImp(itemSyncHScrollView))


        //TODO 为了解决因为设置了Item的点击事件与RecycleView触摸事件冲突导致无法滑动，这里需要对item的触摸事件响应分发，但是效果还是不太好，没有使用ListView流畅
        helper.itemView.setOnTouchListener(ListViewAndHeadViewTouchListener())
    }

    internal inner class OnScrollChangedListenerImp(var mScrollViewArg: SyncHScrollView) :
            SyncHScrollView.OnScrollChangedListener {

        override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
            mScrollViewArg.smoothScrollTo(l, t)
        }
    }


    internal inner class ListViewAndHeadViewTouchListener : View.OnTouchListener {

        override fun onTouch(arg0: View, event: MotionEvent): Boolean {
            // 当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
            val headScrollView = mHeadSyncRecyclerView
            headScrollView.onTouchEvent(event)
            return false
        }
    }
}
