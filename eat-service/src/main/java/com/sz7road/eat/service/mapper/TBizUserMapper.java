package com.sz7road.eat.service.mapper;

import com.sz7road.eat.api.base.IBaseMapper;
import com.sz7road.eat.api.entity.biz.TBizUser;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author Panda.Zh
 * @since 2017-03-23
 */
public interface TBizUserMapper extends IBaseMapper<TBizUser> {

    TBizUser selectByAccountPassword(TBizUser user);
}