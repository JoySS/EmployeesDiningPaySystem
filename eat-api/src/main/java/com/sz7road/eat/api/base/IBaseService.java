package com.sz7road.eat.api.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Panda.Z
 */
public abstract class IBaseService<T extends IBaseModel> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected BaseMapper<T> mapper;

    public T getById(Long id) {
        try {
            return mapper.selectById(id);
        } catch (Exception e) {
            logger.error("根据ID查询实体异常:{}", e.getMessage(), e);
            return null;
        }
    }

    public Page<T> getPage(T entity, Page<T> page) {
        entity.setEnable(1);
        EntityWrapper<T> wrapper = new EntityWrapper<>(entity);
        int count = mapper.selectCount(wrapper);
        page.setTotal(count);
        if (count > 0 && count > page.getCurrent()) {
            List<T> list = mapper.selectPage(page, new EntityWrapper<>(entity));
            page.setRecords(list);
        } else {
            page.setRecords(new ArrayList<T>());
        }
        return page;
    }

    @Transactional
    public boolean insert(T record) {
        try {
            Date date = new Date();
            record.setUpdateTime(date);
            record.setCreateTime(date);
            int row = mapper.insert(record);
            return row == 1;
        } catch (Exception e) {
            logger.error("插入记录失败:{}", e.getMessage(), e);
            return false;
        }
    }

    @Transactional
    public boolean update(T record) {
        try {
            int row;
            record.setUpdateTime(new Date());
            if (record.getId() == null) {
                record.setCreateTime(new Date());
                row = mapper.insert(record);
            } else {
                row = mapper.updateById(record);
            }
            return row == 1;
        } catch (Exception e) {
            logger.error("更新记录失败:{}", e.getMessage(), e);
            return false;
        }
    }
}
