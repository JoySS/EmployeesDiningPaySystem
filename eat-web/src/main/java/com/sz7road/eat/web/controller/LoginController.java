package com.sz7road.eat.web.controller;

import com.sz7road.eat.api.entity.biz.TBizUser;
import com.sz7road.eat.api.service.UserService;
import com.sz7road.eat.api.utils.MD5Util;
import com.sz7road.eat.api.utils.SecurityUtil;
import com.sz7road.eat.web.common.SimpleResult;
import com.sz7road.eat.web.core.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.sz7road.eat.web.common.ErrorCode.ILLEGAL_USER_INFO;

/**
 * @author Panda.Z
 */
@RestController
@Api(value = "用户相关", description = "用户相关")
public class LoginController extends AbstractController {

    @Autowired
    private UserService userService;

    /**
     * http://localhost:8080/login?username=panda&password=ce61649168c4550c2f7acab92354dc6e
     *
     * @param username 初始panda
     * @param password 初始panda(md5=ce61649168c4550c2f7acab92354dc6e)
     */
    @ApiOperation(value = "登录接口")
    @GetMapping("login")
    public Object login(@RequestParam String username, @RequestParam String password) {
        Assert.notNull(username, "账号不能为空");
        Assert.notNull(password, "密码不能为空");
        UsernamePasswordToken token = new UsernamePasswordToken(username, SecurityUtil.encryptPassword(password));
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        if (subject.isAuthenticated()) {
            TBizUser user = new TBizUser();
            user.setAccount(username);
            user.setPassword(password);
            return returnOK(user);
        } else {
            return returnError(ILLEGAL_USER_INFO);
        }
    }

    @ApiOperation(value = "登出接口")
    @GetMapping("logout")
    public SimpleResult<?> logout() {
        return returnOK();
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.digestCrypt("panda"));
    }
}
