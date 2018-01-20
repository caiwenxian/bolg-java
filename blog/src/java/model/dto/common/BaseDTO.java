package model.dto.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-18 17:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BaseDTO implements Serializable{

    private static final long serialVersionUID = -3558525794931360478L;

    /**
     * 每显示数量
     */
    protected Integer limit = 10;
    /**
     * 当前页
     */
    protected Integer page = 1;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}


