package com.hello.stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StockServiceImpl {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StockDao stockDao ;
	
	public Stock getStockById(int id)
	{
		Stock obj = stockDao.getStockById(id);
		
		return obj;
	}
	
	public Stock updateStockById(int id , String stockName)
	{
		Stock obj = stockDao.getStockById(id);
		
		obj.setStockName(stockName);
		
		stockDao.updateStock(obj);
		
		return obj;
		
	}
	

}
