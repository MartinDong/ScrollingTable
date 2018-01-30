package com.example.kotlin.scrollingtable;

/**
 * Created by xiaoyulaoshi on 2018/1/30.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kotlin.scrollingtable.model.ProductModel;

import java.util.List;

/**
 * recyclerView的适配器，为每个item设置点击设置回调；要继承View.OnClickListener
 */
public class MyAdatper extends RecyclerView.Adapter<MyAdatper.MyViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<ProductModel> mList;
    private OnItemClickListener mOnItemClickListener = null;

    //用一个集合将适配中创建的所有 holder对象存储到这个容器中，因为本类中有对holder中的控件创建了监听事件
    //如果不取消监听，可能会出现内存泄漏的问题，所以将holder保存，创建一个方法将绑定监听事件的控件的监听事件至空(个人观点)
    private List<MyViewHolder> mListHolder;

    //添加一个销毁的方法，这样可以加快回收，可以解决内存泄漏的问题，
    //可以用android studio 进行查看内存，多次开启和关闭程序，查看内存内存走势，
    //如果不填加次方法，那么内存会一直增加，并且点GC 内存也不会下降。
    //在activity的销毁方法中添加该方法，就解决了内存泄漏的方法。(这个方法不是最好的，但目前想到的解决方案只有这个，欢迎指点留言)
    public void onDestroy() {
        mList.clear();
        mList = null;
        for (int i = 0; i < mListHolder.size(); i++) {
            mListHolder.get(i).itemView.setOnClickListener(null);
        }
        mListHolder.clear();
        mListHolder = null;
    }


    public MyAdatper(Context mContext, List<ProductModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    /**
     * 创建一个监听事件的接口；重要
     */
    public interface OnItemClickListener {
        void onClick(View v, int position);
    }

    /**
     * 外界进行调用该方法，为item设置点击事件；重要
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    @Override
    public MyAdatper.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product_layout, parent, false);
        //为每个item设置点击事件；
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    /**
     * 这个是继承View.OnClickListener 实现的方法; 重要
     *
     * @param v 当前被点击的v;
     */
    @Override
    public void onClick(View v) {
        //判断当前是否为item设置监听事件
        if (mOnItemClickListener != null) {
            //如果设置了，那么回调该方法，由于view的tag是object类型的，希望能回调到当前所显示到第几项item所以进行类型转换，希望有更好的方法请赐教；
            mOnItemClickListener.onClick(v, Integer.parseInt((String) v.getTag()));
        }
    }

    @Override
    public void onBindViewHolder(final MyAdatper.MyViewHolder holder, int position) {
        //一定要设置这个。要不在回调方法里面获得不到当前点击的是第几个item;注意tag是object类型的；重要；
        holder.itemView.setTag(position + "");
        holder.tvProctName.setText(mList.get(position).getProductName());

        RvAdapter rvAdapter=new RvAdapter();

        rvAdapter.setNewData(mList.get(position).getMPriceList());
        holder.rvProductList.setAdapter(rvAdapter);
        holder.rvProductList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemView.callOnClick();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    /**
     * 希望读者有良好的编码习惯，将ViewHolder类写成静态的.
     **/
    static class MyViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView tvProctName;
        RecyclerView rvProductList;

        public MyViewHolder(View itemView) {
            super(itemView);
            //重要
            this.itemView = itemView;
            tvProctName = itemView.findViewById(R.id.tv_product_name);
            rvProductList = itemView.findViewById(R.id.rv_list_right);
        }
    }

}