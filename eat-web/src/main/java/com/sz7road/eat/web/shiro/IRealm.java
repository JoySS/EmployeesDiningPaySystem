package com.sz7road.eat.web.shiro;

import com.sz7road.eat.api.entity.biz.TBizUser;
import com.sz7road.eat.api.service.UserService;
import com.sz7road.eat.web.utils.WebUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Panda.Z
 */
public class IRealm  extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 初次赋权
     */
    @SuppressWarnings("ConstantConditions")
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 权限系统查询权限给账号赋权
//        info.addRole("xxxx");
//        info.addStringPermission("xxxx");
        return info;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken simpleToken = (UsernamePasswordToken) token;
        StringBuilder password = new StringBuilder(100);
        for (int i = 0; i < simpleToken.getPassword().length; i++) {
            password.append(simpleToken.getPassword()[i]);
        }
        TBizUser param = new TBizUser();
        param.setAccount(simpleToken.getUsername());
        param.setPassword(password.toString());
        TBizUser user = userService.queryByAccountPassword(param);
        if (null != user){
            WebUtil.saveCurrentUser(user);
            return new SimpleAuthenticationInfo(simpleToken.getUsername(), password.toString(), getName());
        }else{
            throw new AuthenticationException();
        }
    }
}
