package com.wenxianm.exception;

/**
 * 查询异常
 *
 * @param
 * @version v1
 * @return class
 */

public class QueryException extends RuntimeException {

    public QueryException(String msg) {
        super(msg);
    }

}
