package exception;

/**
 * 查询异常
 *
 * @param
 * @return class
 * @version v1
 */

public class QueryException extends RuntimeException {

    public QueryException(String msg) {
        super(msg);
    }

}
