package controller.security.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 客服用户
 * author: wenxian.cai
 * date: 2017/9/25 11:12
 */
public class UserRealm extends AuthorizingRealm {
    public void setCacheManager(CacheManager cacheManager) {
        super.setCacheManager(cacheManager);
    }

//    @Autowired
//    CustomerUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        LoginUser user = ClientManager.getClient().getUser();
       /* TAdminRole role = adminService.getAdminRole(user.getRoleId());
        if (role != null) {
            Set<String> roles = new HashSet<>();
            roles.add(role.getName());
            info.setRoles(roles);
            Set<String> pers = adminService.getCurrentAdminPermissionSet(role.getId());
            Iterator<String> it = pers.iterator();
            while (it.hasNext()) {
                String str = it.next();
                System.out.println("str:" + str);
            }
            //  pers.removeAll(pers);


            info.setStringPermissions(pers);
            //TAdminPermission permission = adminService.getAdminPermission(role.getPermissionId());
            *//*Set<String> permissionSet = new HashSet<>();
            String[] pe = permission.getMenu().split(",");
            for (String p : pe) {
                permissionSet.add(p);
            }*//*
            //info.setStringPermissions(permissionSet);
            //info.setObjectPermissions();
        }*/
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UserToken userToken = (UserToken) authenticationToken;
//        String userName = userToken.getUsername();
//        CustomerUserPO user = userService.getUserByUserName(userName);
//        if (user == null) {
//            throw new UnknownAccountException("该账号不存在");
//        }
//        return new SimpleAuthenticationInfo(userName, user.getPassword(), getName());
        return new SimpleAuthenticationInfo("mueju", "000000", getName());
    }

    /**
     * 为权限标签扩展or、and、not功能
     *
     * @param principals
     * @param permission
     * @return
     */
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        if (permission.contains("or") && permission.contains(" ")) {
            String[] permissions = permission.split("or");
            for (String orPermission : permissions) {
                if (isPermittedWithNotOperator(principals, orPermission)) {
                    return true;
                }
            }
            return false;
        } else if (permission.contains("and") && permission.contains(" ")) {
            String[] permissions = permission.split("and");
            for (String orPermission : permissions) {
                if (!isPermittedWithNotOperator(principals, orPermission)) {
                    return false;
                }
            }
            return true;
        } else {
            return isPermittedWithNotOperator(principals, permission);
        }
    }

    private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
        if (permission.startsWith("not")) {
            return !super.isPermitted(principals, permission.substring(3));
        } else {
            return super.isPermitted(principals, permission);
        }
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
