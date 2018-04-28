package com.hello.stock;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class StockDao {
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
	
}
