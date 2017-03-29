package com.sz7road.eat.service.impl;


import com.sz7road.eat.api.entity.biz.TBizUser;
import com.sz7road.eat.api.service.UserService;
import com.sz7road.eat.service.mapper.TBizUserMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Panda.Zh
 * @since 2017-03-19
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UserServiceImpl extends UserService {

    public TBizUser queryByAccountPassword(TBizUser user){
        return ((TBizUserMapper) mapper).selectByAccountPassword(user);
    }

}
