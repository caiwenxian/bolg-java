<<<<<<< HEAD
package controller.security.credential;


import controller.security.token.UserToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import utils.MD5Utils;


/**
 *
 * author: wenxian.cai
 * date: 2017/9/25 11:12
 */
public class CustomCredentials extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token,
        AuthenticationInfo info) {
        UserToken userToken = (UserToken) token;
        String pwd = MD5Utils.encryptPassword(String.valueOf(
                    userToken.getPassword()));
        boolean matches = equals(pwd, getCredentials(info));

        return matches;
    }
}
=======
package controller.security.credential;


import controller.security.token.UserToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import utils.MD5Utils;


/**
 *
 * author: wenxian.cai
 * date: 2017/9/25 11:12
 */
public class CustomCredentials extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token,
        AuthenticationInfo info) {
        UserToken userToken = (UserToken) token;
        String pwd = MD5Utils.encryptPassword(String.valueOf(
                    userToken.getPassword()));
        boolean matches = equals(pwd, getCredentials(info));

        return matches;
    }
}
>>>>>>> origin/master
