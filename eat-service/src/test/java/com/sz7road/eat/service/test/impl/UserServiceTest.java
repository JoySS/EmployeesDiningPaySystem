package com.sz7road.eat.service.test.impl;

import com.sz7road.eat.api.entity.biz.TBizUser;
import com.sz7road.eat.api.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Panda.Z
 */
@SuppressWarnings("ALL")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath:spring/spring-config.xml")
})
public class UserServiceTest {

    @Autowired
    private UserService userService;

//    @Test
    public void getById(){
        TBizUser user = userService.getById(1L);
        System.out.println(user);
    }

//    @Test
    public void update(){
        TBizUser user = userService.getById(1L);
        userService.update(user);
    }

}
