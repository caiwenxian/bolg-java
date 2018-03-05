package model.po.common;

import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-02-07 10:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface Page<T> {


    int init();

    List<T> initData(int pageIndex, int pageSize);

}
