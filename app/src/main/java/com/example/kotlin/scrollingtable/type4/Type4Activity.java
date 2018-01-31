package com.example.kotlin.scrollingtable.type4;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.kotlin.scrollingtable.R;
import com.example.kotlin.scrollingtable.type4.model.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 最完美实现，使用 ListView + HorizontalScrollView 实现
 * Created by xiaoyulaoshi on 2018/1/31.
 */
public class Type4Activity extends Activity {
    ListView mListView1;
    RelativeLayout mHead;
    HolderAdapter holderAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type4);

        mHead = findViewById(R.id.head);
        mHead.setFocusable(true);
        mHead.setClickable(true);
        mHead.setBackgroundColor(Color.BLACK);
        mHead.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());


        mListView1 = findViewById(R.id.listView1);
        mListView1.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
        mListView1.setCacheColorHint(0);

        // 创建当前用于显示视图的数据
        List<Data> currentData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Data data = new Data();
            data.setStr1("股票>>" + i);
            data.setStr2("价格>>1");
            data.setStr3("价格>>2");
            data.setStr4("价格>>3");
            data.setStr5("价格>>4");
            data.setStr6("价格>>5");
            data.setStr7("价格>>6");
            data.setStr8("价格>>7");
            currentData.add(data);
        }


        holderAdapter = new HolderAdapter(this, R.layout.item_layout_type4, currentData, mHead);
        mListView1.setAdapter(holderAdapter);
        // OnClick监听
        mListView1.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Log.i("Type4Activity ListView", "onItemClick Event");
                Toast.makeText(Type4Activity.this, "点了第" + arg2 + "个",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    class ListViewAndHeadViewTouchLinstener implements View.OnTouchListener {

        public boolean onTouch(View arg0, MotionEvent arg1) {
            // 当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
            HorizontalScrollView headSrcrollView = mHead.findViewById(R.id.horizontalScrollView1);
            headSrcrollView.onTouchEvent(arg1);
            return false;
        }
    }

}
