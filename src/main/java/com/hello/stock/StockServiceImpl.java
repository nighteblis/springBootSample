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
	

}
