package com.sz7road.eat.web.controller;

import com.sz7road.eat.api.service.UserService;
import com.sz7road.eat.web.core.AbstractController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Panda.Z
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户相关", description = "用户相关")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

}
