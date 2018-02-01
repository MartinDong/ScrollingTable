# ScrollingTable
> 要求：可以上下左右移动的表格布局，仿同花顺自选列表，老虎证券财报列表

## [下载APK](http://fir.im/4e96)
![](/doc/下载链接二维码.png)


| 同花顺效果  | 老虎证券效果|  最终实现效果如图|  
| --------   | -----|  -----| 
| <img src="/doc/Gif_20180201_224640.gif" width="250px"/>  | <img src="/doc/Gif_20180201_225941.gif" width="250px"/> | <img src="/doc/Gif_20180201_224941.gif" width="250px"/> | 


## 实现思路分析
<img src="/doc/深度截图_选择区域_20180201223904.png" width="480px"/>

### 视图分析
1、**主视图分为： 头部控件（HeadView）+下面的ListView**

2、**头部控件（HeadView）：左边为 TextView，右边为 HorizontalScrollView**

3、**ListView 条目视图：左边为 TextView，右边为 HorizontalScrollView**

#### 视图联动分析
1、**头部 HorizontalScrollView 滑动事件广播通知 ListView 条目中的 HorizontalScrollView 从而实现联动效果**

2、**拦截 ListView 单个条目中的 HorizontalScrollView 滑动事件，防止 ListView 的触摸事件和 HorizontalScrollView 触摸事件冲突**

3、**统一处理 ListView 和 头部控件（HeadView）触摸事件，统一将触摸事件传递给 头部控件（HeadView）右边的 HorizontalScrollView ，从而实现（1）中的效果**

````java
 /**
  * Created by xiaoyulaoshi on 2018/1/31.
  *
  * 自定义的 滚动控件
  * 重载了 [SyncHScrollView.onScrollChanged]（滚动条变化）,监听每次的变化通知给观察(此变化的)观察者
  * 可使用 [SyncHScrollView.AddOnScrollChangedListener] 来订阅本控件的 滚动条变化
  */
 class SyncHScrollView : HorizontalScrollView {
     internal var mScrollViewObserver: ScrollViewObserver? = ScrollViewObserver()
 
     constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}
 
     constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}
 
     constructor(context: Context) : super(context) {}
 
     override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
         /*
          * 当滚动条移动后，引发 滚动事件。通知给观察者，观察者会传达给其他的条目中的滚动视图。
 		 */
         if (mScrollViewObserver != null) {
             mScrollViewObserver!!.NotifyOnScrollChanged(l, t, oldl, oldt)
         }
         super.onScrollChanged(l, t, oldl, oldt)
     }
 
     /*
      * 订阅 本控件 的 滚动条变化事件
      * */
     fun AddOnScrollChangedListener(listener: OnScrollChangedListener) {
         mScrollViewObserver!!.AddOnScrollChangedListener(listener)
     }
 
     /*
      * 取消 订阅 本控件 的 滚动条变化事件
      * */
     fun RemoveOnScrollChangedListener(listener: OnScrollChangedListener) {
         mScrollViewObserver!!.RemoveOnScrollChangedListener(listener)
     }
 
     /*
      * 当发生了滚动事件时
      */
     interface OnScrollChangedListener {
         fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int)
     }
 
     /**
      * 观察者
      */
     class ScrollViewObserver {
         internal var mList: MutableList<OnScrollChangedListener>? = null
 
         init {
             mList = ArrayList()
         }
 
         fun AddOnScrollChangedListener(listener: OnScrollChangedListener) {
             mList!!.add(listener)
         }
 
         fun RemoveOnScrollChangedListener(
                 listener: OnScrollChangedListener) {
             mList!!.remove(listener)
         }
 
         fun NotifyOnScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
             if (mList == null || mList!!.size == 0) {
                 return
             }
             for (i in mList!!.indices) {
                 if (mList!![i] != null) {
                     mList!![i].onScrollChanged(l, t, oldl, oldt)
                 }
             }
         }
     }
 }
````

````java
/**
* ListView使用的数据适配器，实现数据填充以及列表右侧的 HorizontalScrollView 与 页面中的 头部控件（HeadView） 右侧 HorizontalScrollView 的绑定
* 
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
                   private val mHead: RelativeLayout) : BaseAdapter() {
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
        holder.txt2!!.text = currentData[position].str1!! + currentData[position].str2!!
        holder.txt3!!.text = currentData[position].str1!! + currentData[position].str3!!
        holder.txt4!!.text = currentData[position].str1!! + currentData[position].str4!!
        holder.txt5!!.text = currentData[position].str1!! + currentData[position].str5!!
        holder.txt6!!.text = currentData[position].str1!! + currentData[position].str6!!
        holder.txt7!!.text = currentData[position].str1!! + currentData[position].str7!!
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
````


````java

/**
 * 最完美实现，使用 ListView + HorizontalScrollView 实现
 * Created by xiaoyulaoshi on 2018/1/31.
 */
class Type4Activity : Activity() {
    internal lateinit var mListView1: ListView
    internal lateinit var mHead: RelativeLayout
    internal lateinit var type4Adapter: Type4Adapter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type4)

        mHead = findViewById(R.id.head)
        mHead.isFocusable = true
        mHead.isClickable = true

        //TODO 划重点：这里需要从传入的列表头拿到里面的右侧滑动控件
        mHead.setOnTouchListener(ListViewAndHeadViewTouchListener())


        mListView1 = findViewById(R.id.lv_produce)
        mListView1.setOnTouchListener(ListViewAndHeadViewTouchListener())

        // 创建当前用于显示视图的数据
        val currentData = ArrayList<Data>()
        for (i in 0..49) {
            val data = Data()
            data.str1 = "股票>" + i
            data.str2 = "价格>1"
            data.str3 = "价格>2"
            data.str4 = "价格>3"
            data.str5 = "价格>4"
            data.str6 = "价格>5"
            data.str7 = "价格>6"
            data.str8 = "价格>7"
            currentData.add(data)
        }


        type4Adapter = Type4Adapter(this, R.layout.item_layout_type4, currentData, mHead)
        mListView1.adapter = type4Adapter
        // OnClick监听
        mListView1.onItemClickListener = OnItemClickListener { arg0, arg1, arg2, arg3 ->
            Log.i("Type4Activity ListView", "onItemClick Event")
            Toast.makeText(this@Type4Activity, "点了第" + arg2 + "个",
                    Toast.LENGTH_SHORT).show()
        }

    }

    /**
     * TODO 划重点：用来将头部和列表上面的触摸事件都分发给头部的滑动控件
     */
    internal inner class ListViewAndHeadViewTouchListener : View.OnTouchListener {

        override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
            // 当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
            val headScrollView = mHead.findViewById<HorizontalScrollView>(R.id.horizontalScrollView1)
            headScrollView.onTouchEvent(arg1)
            return false
        }
    }

}
````