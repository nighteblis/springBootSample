package com.hello.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.hello.common.exception.Assert;
import com.hello.model.BaseModel;
import com.hello.repository.BaseRepository;
import com.hello.service.BaseService;



public abstract class BaseServiceImpl<T extends BaseModel, R extends BaseRepository<T>> implements BaseService<T> {

    protected R repository;

    protected abstract void setRepository(R repository);

    @Transactional
    protected   T save(T entity) {
        Assert.notNull(entity, "对象");

        Date date = new Date();

        entity.setCreateTime(date);
        entity.setUpdateTime(date);

        return repository.save(entity);
    }

    @Transactional
    protected  T update(T entity) {
        Assert.notNull(entity, "对象");
        Assert.notNull(entity.getId(), "主键");

        Date date = new Date();

        entity.setUpdateTime(date);

        return repository.save(entity);
    }


    public T get(Long id) {
        Assert.notNull(id, "主键");
        return repository.findOne(id);
    }


    public T load(Long id) {
        T entity = get(id);
        Assert.notExist(entity, "对象");
        return entity;
    }


    public List<T> findAll() {
        return repository.findAll();
    }


    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

}
