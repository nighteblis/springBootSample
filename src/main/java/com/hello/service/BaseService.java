package com.hello.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.hello.model.BaseModel;

public interface BaseService<T extends BaseModel> {

    T get(Long id);

    T load(Long id);

    List<T> findAll();

    List<T> findAll(Sort sort);

}
