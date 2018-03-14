package model.vo.user;

/**
 * 返回前端的登录凭证
 *
 * @Author: [caiwenxian]
 * @Date: [2018-03-10 16:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class TokenVO {

    /**
     * token
     */
    private String token;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 签名
     */
    private String sign;

    public TokenVO() {
    }

    public TokenVO(String token) {
        this.token = token;
    }

    public TokenVO(String token, String timestamp, String sign) {
        this.token = token;
        this.timestamp = timestamp;
        this.sign = sign;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
