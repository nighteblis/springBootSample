package com.hello.stock;

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
import org.springframework.stereotype.Repository;

import com.hello.repository.BaseRepository;

@Transactional
@Repository
public class StockRepository implements BaseRepository{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PersistenceContext	
	private EntityManager entityManager;	
	
	

	public Stock getStockById(int id) {
		return entityManager.find(Stock.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Stock> getAllArticles() {
		String hql = "FROM Article as atcl ORDER BY atcl.articleId";
		return (List<Stock>) entityManager.createQuery(hql).getResultList();
	}	

	public void addStock(Stock stock) {
		entityManager.persist(stock);
	}

	public void updateStock(Stock stock) {
		Stock artcl = getStockById(stock.getId());
		artcl.setStockId(stock.getStockId());
		artcl.setStockName(stock.getStockName());
		entityManager.flush();
	}

	public void deleteStock(int id) {
		entityManager.remove(getStockById(id));
	}

	public boolean stockExists(String stockId, String stockName) {
		String hql = "FROM stock as stocks WHERE stocks.stockId = ? and stocks.stockName = ?";
		int count = entityManager.createQuery(hql).setParameter(1, stockId)
		              .setParameter(2, stockName).getResultList().size();
		
		return count > 0 ? true : false;
	}

	@Override
	public Object save(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable save(Iterable entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findOne(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable findAll(Iterable ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
