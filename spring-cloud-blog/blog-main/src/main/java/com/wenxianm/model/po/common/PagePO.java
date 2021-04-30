package com.wenxianm.model.po.common;

import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-02-07 11:26]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PagePO<T> {

    /**
     * 总条数
     */
    private int totalSize;

    /**
     * 数据
     */
    private List<T> data;

    public PagePO(int totalSize, List<T> data) {
        this.totalSize = totalSize;
        this.data = data;
    }

    @Override
    public String toString() {
        return "PagePO{" +
                "totalSize=" + totalSize +
                ", data=" + data +
                '}';
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
