package service.common.impl;

import service.common.Result;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-26 11:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ActResult implements Result {

    private int code = 0;
    private String msg;
    private Object data;

    public ActResult() {
    }

    public ActResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ActResult success(String msg) {
        return new ActResult(0, msg, null);
    }

    public static ActResult success() {
        return new ActResult(0, "操作成功", null);
    }

    public static ActResult error(int code, String msg) {
        return new ActResult(code, msg, null);
    }

    public static ActResult error(String msg) {
        return new ActResult(303, msg, null);
    }

    public static ActResult error() {
        return new ActResult(Result.CODE_SERVE_ERROR, "操作失败", null);
    }

    public static ActResult data(Object data) {
        return new ActResult(0, null, data);
    }

    @Override
    public String toString() {
        return "ActResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
