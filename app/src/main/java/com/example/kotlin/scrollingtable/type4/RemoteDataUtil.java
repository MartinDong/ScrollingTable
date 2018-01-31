package com.example.kotlin.scrollingtable.type4;

/**
 * Created by xiaoyulaoshi on 2018/1/31.
 */

import com.example.kotlin.scrollingtable.type4.model.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mush
 *
 */
public class RemoteDataUtil {

    /**
     * @param currentPage
     * @param pageSize
     * @return 模拟网络数据
     */
    public static List<Data> createUpdateData(int currentPage, int pageSize) {
        List<Data> list = new ArrayList<Data>();
        for (int i = (currentPage - 1) * pageSize; i < currentPage * pageSize; i++) {
            Data data = new Data();
            data.setStr1(i + "行");
            data.setStr2(i + "");
            data.setStr3(i + "");
            data.setStr4(i + "");
            data.setStr5(i + "");
            data.setStr6(i + "");
            data.setStr7(i + "");
            data.setStr8(i + "");
            list.add(data);
        }
        return list;
    }

    // 模拟联接网络以及从网络中获取数据花费的时间
    public static void setRemoteDataByPage(final int currentPage,
                                           final int pageSize, final LoadStateInterface onLoadComplete) {
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 获取当前要更新的数据
                List<Data> updateDataList = createUpdateData(currentPage,
                        pageSize);
                // 需要更新的数据加入当前数据集合
                onLoadComplete.onLoadComplete(updateDataList);

            }
        }.start();

    }
}