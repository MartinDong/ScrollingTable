package com.example.kotlin.scrollingtable.type6


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.kotlin.scrollingtable.R
import com.example.kotlin.scrollingtable.type4.model.ProductData
import com.example.kotlin.scrollingtable.type4.view.SyncHScrollView

/**
 * 列表数据适配器
 * Created by xiaoyulaoshi on 2018/1/31.
 */
class Type6Adapter(context: Context,
                   /**
                    * List中的数据
                    */
                   private val currentData: MutableList<ProductData>,
                   mListView: ListView,
                   private var mHeadStickyHSView: SyncHScrollView?) : BaseAdapter() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    //列表中的分组头部右侧的滑动视图
    private var mHeadGroupScrollView: SyncHScrollView? = null

    init {
        mListView.setOnTouchListener(ListViewAndHeadViewTouchListener())
        mHeadStickyHSView?.setOnTouchListener(ListViewAndHeadViewTouchListener())
    }

    override fun getCount(): Int {
        return this.currentData.size
    }

    override fun getItem(position: Int): Any? {
        return currentData[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    // 每个convert view都会调用此方法，获得当前所需要的view样式
    override fun getItemViewType(position: Int): Int {
        return currentData[position].typeItem
    }

    override fun getViewTypeCount(): Int {
        return 3
    }


    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parentView: ViewGroup): View? {
        var convertView = convertView
        var holder1: ViewHolder1? = null
        var holder2: ViewHolder2? = null
        var holder3: ViewHolder3? = null

        //获取当前的条目数据类型
        val typeItem = getItemViewType(position)

        if (convertView == null) {
            when (typeItem) {
                0 -> {
                    convertView = mInflater.inflate(R.layout.item_layout_type4, null)
                    holder1 = ViewHolder1()

                    //获取当前条目中的右侧滑动控件
                    val scrollView1 = convertView!!.findViewById<SyncHScrollView>(R.id.horizontalScrollView1)
                    //TODO 划重点：这里需要从传入的列表头拿到里面的右侧滑动控件
                    //将当前条目的右侧滑动控件添加到头部滑动控件的滑动观察者集合中
                    mHeadGroupScrollView?.AddOnScrollChangedListener(OnScrollChangedListenerImp(scrollView1))
                    mHeadStickyHSView?.AddOnScrollChangedListener(OnScrollChangedListenerImp(scrollView1))

                    //进行holder的初始化操作
                    holder1.scrollView = scrollView1
                    holder1.txt1 = convertView.findViewById(R.id.textView1)
                    holder1.txt2 = convertView.findViewById(R.id.textView2)
                    holder1.txt3 = convertView.findViewById(R.id.textView3)
                    holder1.txt4 = convertView.findViewById(R.id.textView4)
                    holder1.txt5 = convertView.findViewById(R.id.textView5)
                    holder1.txt6 = convertView.findViewById(R.id.textView6)
                    holder1.txt7 = convertView.findViewById(R.id.textView7)

                    convertView.tag = holder1
                }

                1 -> {
                    convertView = mInflater.inflate(R.layout.item_layout_type6, null)
                    holder2 = ViewHolder2()

                    convertView.tag = holder2
                }

                2 -> {
                    convertView = mInflater.inflate(R.layout.item_layout_type6_group, null)
                    holder3 = ViewHolder3()

                    mHeadGroupScrollView = convertView.findViewById(R.id.horizontalScrollView1)
                    holder3.scrollView = convertView.findViewById(R.id.horizontalScrollView1)
                    convertView.tag = holder3
                }
            }

        } else {
            when (typeItem) {
                0 -> {
                    holder1 = convertView.tag as ViewHolder1
                }
                1 -> {
                    holder2 = convertView.tag as ViewHolder2
                }
                2 -> {
                    holder3 = convertView.tag as ViewHolder3
                }
            }
        }
        when (typeItem) {
            0 -> {
                holder1?.txt1!!.text = currentData[position].str1
                holder1.txt2!!.text = currentData[position].str2!!
                holder1.txt3!!.text = currentData[position].str3!!
                holder1.txt4!!.text = currentData[position].str4!!
                holder1.txt5!!.text = currentData[position].str5!!
                holder1.txt6!!.text = currentData[position].str6!!
                holder1.txt7!!.text = currentData[position].str7!!
            }
        }
        return convertView
    }

    open class OnScrollChangedListenerImp(var mScrollViewArg: SyncHScrollView) :
            SyncHScrollView.OnScrollChangedListener {

        override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
            mScrollViewArg.smoothScrollTo(l, t)
        }
    }

    /**
     * TODO 划重点：用来将头部和列表上面的触摸事件都分发给头部的滑动控件
     */
    internal inner class ListViewAndHeadViewTouchListener : View.OnTouchListener {

        override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
            // 当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
            mHeadGroupScrollView?.onTouchEvent(arg1)
            mHeadStickyHSView?.onTouchEvent(arg1)
            return false
        }
    }

    internal inner class ViewHolder1 {
        var txt1: TextView? = null
        var txt2: TextView? = null
        var txt3: TextView? = null
        var txt4: TextView? = null
        var txt5: TextView? = null
        var txt6: TextView? = null
        var txt7: TextView? = null
        var scrollView: HorizontalScrollView? = null
    }

    internal inner class ViewHolder2 {
        var txt1: TextView? = null
        var iv_icon: ImageView? = null
    }

    internal inner class ViewHolder3 {
        //右侧的滑动控件
        var scrollView: SyncHScrollView? = null

    }
}
