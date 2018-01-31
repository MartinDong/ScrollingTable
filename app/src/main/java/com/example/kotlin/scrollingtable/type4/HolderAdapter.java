package com.example.kotlin.scrollingtable.type4;

/**
 * Created by xiaoyulaoshi on 2018/1/31.
 */

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kotlin.scrollingtable.R;
import com.example.kotlin.scrollingtable.type4.model.Data;

import java.util.List;

public class HolderAdapter extends BaseAdapter {
    private static final String TAG = HolderAdapter.class.getName();

    /**
     * List中的数据
     */
    private List<Data> currentData;
    /**
     * ListView头部
     */
    private RelativeLayout mHead;
    /**
     * layout ID
     */
    private int id_row_layout;
    private LayoutInflater mInflater;

    int[] colors = {Color.rgb(102, 102, 51), Color.rgb(153, 153, 51)};
    // int[] colors = { Color.BLACK, Color.BLACK };

    public HolderAdapter(Context context, int id_row_layout,
                         List<Data> currentData, RelativeLayout mHead) {
        Log.v(TAG + ".HolderAdapter", " 初始化");

        this.id_row_layout = id_row_layout;
        this.mInflater = LayoutInflater.from(context);
        this.currentData = currentData;
        this.mHead = mHead;

    }

    public int getCount() {
        return this.currentData.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    /**
     * 向List中添加数据
     *
     * @param items
     */
    public void addItem(List<Data> items) {
        for (Data item : items) {
            currentData.add(item);
        }
    }

    /**
     * 清空当List中的数据
     */
    public void cleanAll() {
        this.currentData.clear();
    }

    public View getView(int position, View convertView, ViewGroup parentView) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(id_row_layout, null);
            holder = new ViewHolder();

            MyHScrollView scrollView1 = (MyHScrollView) convertView
                    .findViewById(R.id.horizontalScrollView1);

            holder.scrollView = scrollView1;
            holder.txt1 = (TextView) convertView.findViewById(R.id.textView1);
            holder.txt2 = (TextView) convertView.findViewById(R.id.textView2);
            holder.txt3 = (TextView) convertView.findViewById(R.id.textView3);
            holder.txt4 = (TextView) convertView.findViewById(R.id.textView4);
            holder.txt5 = (TextView) convertView.findViewById(R.id.textView5);

            MyHScrollView headSrcrollView = (MyHScrollView) mHead
                    .findViewById(R.id.horizontalScrollView1);
            headSrcrollView
                    .AddOnScrollChangedListener(new OnScrollChangedListenerImp(scrollView1));

            convertView.setTag(holder);
            // 隔行变色
//            convertView.setBackgroundColor(colors[position % 2]);
            // mHolderList.add(holder);
        } else {
            // 隔行变色
//            convertView.setBackgroundColor(colors[position % 2]);
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txt1.setText(currentData.get(position).getStr1() + 1 + "列");
        holder.txt2.setText(currentData.get(position).getStr1() + 2 + "列");
        holder.txt3.setText(currentData.get(position).getStr1() + 3 + "列");
        holder.txt4.setText(currentData.get(position).getStr1() + 4 + "列");
        holder.txt5.setText(currentData.get(position).getStr1() + 5 + "列");

        return convertView;
    }

    class OnScrollChangedListenerImp implements MyHScrollView.OnScrollChangedListener {
        MyHScrollView mScrollViewArg;

        public OnScrollChangedListenerImp(MyHScrollView scrollViewar) {
            mScrollViewArg = scrollViewar;
        }

        public void onScrollChanged(int l, int t, int oldl, int oldt) {
            mScrollViewArg.smoothScrollTo(l, t);
        }
    }

    class ViewHolder {
        TextView txt1;
        TextView txt2;
        TextView txt3;
        TextView txt4;
        TextView txt5;
        HorizontalScrollView scrollView;
    }

}
