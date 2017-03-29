package com.sz7road.eat.api.service;


import com.sz7road.eat.api.base.IBaseService;
import com.sz7road.eat.api.entity.biz.TBizUser;

/**
 * <p>
 * 用户表  服务实现类
 * </p>
 *
 * @author Panda.Zh
 * @since 2017-03-19
 */
public abstract class UserService extends IBaseService<TBizUser> {

    public abstract TBizUser queryByAccountPassword(TBizUser user);

}
