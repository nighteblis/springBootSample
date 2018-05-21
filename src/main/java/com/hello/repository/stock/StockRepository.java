package com.hello.repository.stock;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hello.model.Stock;
import com.hello.repository.BaseRepository;

/**
 * 
 * automatically pick up by spring jpa
 * 
 * @author nighteblis
 *
 */

public interface StockRepository extends BaseRepository<Stock>{

	Stock findByStockId(int stockId);
	
	
}
