package service.common;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-26 11:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface Result {

    /**
     * 消息码
     */
    int getCode();

    /**
     * 错误消息
     */
    String getMsg();

    /**
     * 返回数据
     */
    Object getData();
}
