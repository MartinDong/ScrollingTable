package com.example.kotlin.scrollingtable.type4;

/**
 * Created by xiaoyulaoshi on 2018/1/31.
 */

import com.example.kotlin.scrollingtable.type4.model.Data;

import java.util.List;

public interface LoadStateInterface {
    /* 加载完成 */
    public void onLoadComplete(List<Data> remotDate);
}
