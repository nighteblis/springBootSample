package com.hello.repository;


import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;


@NoRepositoryBean
public  interface BaseRepository<T,Id extends Serializable> extends  PagingAndSortingRepository<T, Id> {

	Iterable<T> findAll();

}
