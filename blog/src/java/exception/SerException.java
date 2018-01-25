package exception;


/**
 * 服务层异常
 *
 * @param
 * @return class
 * @version v1
 */
public class SerException extends Exception {

    private static final long serialVersionUID = 6229315095728639413L;

    public SerException() {
        super();
    }

    public SerException(String message) {
        super(message);
    }
}
