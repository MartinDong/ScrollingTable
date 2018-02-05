package com.example.kotlin.scrollingtable.type4


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.HorizontalScrollView
import android.widget.TextView
import com.example.kotlin.scrollingtable.R
import com.example.kotlin.scrollingtable.type4.model.Data
import com.example.kotlin.scrollingtable.type4.view.SyncHScrollView

/**
 * 列表数据适配器
 * Created by xiaoyulaoshi on 2018/1/31.
 */
class Type4Adapter(context: Context,
                   /**
                    * layout ID
                    */
                   private val id_row_layout: Int,
                   /**
                    * List中的数据
                    */
                   private val currentData: MutableList<Data>,
                   /**
                    * ListView头部
                    */
                   private val mHead: View) : BaseAdapter() {
    private val mInflater: LayoutInflater


    init {
        Log.v(TAG + ".Type4Adapter", " 初始化")
        this.mInflater = LayoutInflater.from(context)

    }

    override fun getCount(): Int {
        return this.currentData.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    /**
     * 向List中添加数据
     *
     * @param items
     */
    fun addItem(items: List<Data>) {
        for (item in items) {
            currentData.add(item)
        }
    }

    /**
     * 清空当List中的数据
     */
    fun cleanAll() {
        this.currentData.clear()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parentView: ViewGroup): View {
        var convertView = convertView
        var holder: ViewHolder? = null
        if (convertView == null) {
            convertView = mInflater.inflate(id_row_layout, null)
            holder = ViewHolder()

            //获取当前条目中的右侧滑动控件
            val scrollView1 = convertView!!.findViewById<SyncHScrollView>(R.id.horizontalScrollView1)

            //TODO 划重点：这里需要从传入的列表头拿到里面的右侧滑动控件
            val headScrollView = mHead.findViewById<SyncHScrollView>(R.id.horizontalScrollView1)
            //将当前条目的右侧滑动控件添加到头部滑动控件的滑动观察者集合中
            headScrollView.AddOnScrollChangedListener(OnScrollChangedListenerImp(scrollView1))


            //进行holder的初始化操作
            holder.scrollView = scrollView1
            holder.txt1 = convertView.findViewById(R.id.textView1)
            holder.txt2 = convertView.findViewById(R.id.textView2)
            holder.txt3 = convertView.findViewById(R.id.textView3)
            holder.txt4 = convertView.findViewById(R.id.textView4)
            holder.txt5 = convertView.findViewById(R.id.textView5)
            holder.txt6 = convertView.findViewById(R.id.textView6)
            holder.txt7 = convertView.findViewById(R.id.textView7)

            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        holder.txt1!!.text = currentData[position].str1
        holder.txt2!!.text = currentData[position].str2!!
        holder.txt3!!.text = currentData[position].str3!!
        holder.txt4!!.text = currentData[position].str4!!
        holder.txt5!!.text = currentData[position].str5!!
        holder.txt6!!.text = currentData[position].str6!!
        holder.txt7!!.text = currentData[position].str7!!
        return convertView
    }

    internal inner class OnScrollChangedListenerImp(var mScrollViewArg: SyncHScrollView) :
            SyncHScrollView.OnScrollChangedListener {

        override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
            mScrollViewArg.smoothScrollTo(l, t)
        }
    }

    internal inner class ViewHolder {
        var txt1: TextView? = null
        var txt2: TextView? = null
        var txt3: TextView? = null
        var txt4: TextView? = null
        var txt5: TextView? = null
        var txt6: TextView? = null
        var txt7: TextView? = null
        var scrollView: HorizontalScrollView? = null
    }

    companion object {
        private val TAG = Type4Adapter::class.java.name
    }

}
