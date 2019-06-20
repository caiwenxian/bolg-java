package exception;


/**
 * 服务层异常
 *
 * @param
 * @version v1
 * @return class
 */
public class SerException extends Exception {

    private static final long serialVersionUID = 6229315095728639413L;

    private int code;

    public SerException() {
        super();
    }

    public SerException(String message) {
        super(message);
    }

    public SerException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
