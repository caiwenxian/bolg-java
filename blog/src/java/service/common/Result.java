package service.common;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-26 11:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface Result {

    public static final String MSG_NOT_LOGIN_OPERATE = "用户未登录，不可操作";

    public static final String MSG_NOT_LOGIN = "用户未登录";

    public static final String MSG_PARAMS_MISS = "参数缺失";

    public static final int CODE_NOT_LOGIN = 401;

    public static final int CODE_SERVE_ERROR = 500;


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
