package com.hello.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hello.common.utils.MyBeanUtils;
import com.hello.model.Stock;
import com.hello.model.StockHisData;
import com.hello.repository.stock.StockRepository2;
import com.hello.repository.stockHisData.StockHisDataRepository;
import com.hello.service.StockService;



@Service
public class StockServiceImpl extends BaseServiceImpl<Stock, StockRepository2> implements StockService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	


	
	public Stock getStockById(Long id)
	{
		Stock obj = repository.findOne(id);		
		
		return obj;
	}
	
	public List<Stock> getStocks(){
		
		List<Stock> stocks = new ArrayList<Stock>();		
		this.findAll().forEach(stock -> {stocks.add((Stock) stock);} );			
		return stocks;
	}
	
	public Stock updateStockById(Long id , Stock stock)
	{
		
		Stock originalStock = this.get(id);		
		MyBeanUtils.copyProperties(stock, originalStock,MyBeanUtils.getNullPropertyNames(stock));		
		logger.info(stock.toString());
		logger.info(originalStock.toString());		
		
		this.save(originalStock);
		//stockDao.updateStock(originalStock);	
		return originalStock;		
	}

	@Override
	@Autowired
	protected void setRepository(StockRepository2 repository) {
		// TODO Auto-generated method stub
		super.repository = repository;
		
	}
	


}
