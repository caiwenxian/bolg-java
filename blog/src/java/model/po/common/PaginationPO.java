package model.po.common;

import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-02-07 10:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PaginationPO implements Page{

    int startRow = 0;

    int endRow = 0;

    int totalSize = 0;


    public PaginationPO() {

    }

    public PaginationPO(Page page) {
        System.out.println("page:" + page.init());
    }

    public PaginationPO(int limit, int pageIndex) {

    }


    @Override
    public int init() {
        return 0;
    }

    @Override
    public List initData(int pageIndex, int pageSize) {
        System.out.println(pageIndex + " " + pageSize);
        return null;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
