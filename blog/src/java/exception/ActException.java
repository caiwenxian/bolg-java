package exception;

/**
 * 控制器异常
 *
 * @param
 * @return class
 * @version v1
 */
public class ActException extends Exception {

    private static final long serialVersionUID = -4245853247366157120L;

    public ActException() {
        super();
    }

    public ActException(String message) {
        super(message);
    }
    public ActException(String message,Throwable throwable) {
        super(message,throwable);
    }
}
