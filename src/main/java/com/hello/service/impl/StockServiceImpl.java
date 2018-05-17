package com.hello.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hello.common.utils.MyBeanUtils;
import com.hello.model.Stock;
import com.hello.model.StockHisData;
import com.hello.repository.stock.StockRepository;
import com.hello.repository.stockHisData.StockHisDataRepository;
import com.hello.service.StockService;



@Service
public class StockServiceImpl extends BaseServiceImpl<Stock, StockRepository> implements StockService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	@Autowired
	protected void setRepository(StockRepository repository) {
		// TODO Auto-generated method stub
		super.repository = repository;
		
	}
	
	public Stock getStockById(Long id)
	{
		Stock obj = repository.findOne(id);		
		
		return obj;
	}
	

	
	
	public List<Stock> getStocks(String pageNumber, String pageSize)
	{		
		
		if(pageNumber == null || pageSize == null)
		{
			pageNumber = "0";
			pageSize = "10";
		}
		
		

		
		Pageable page = new PageRequest(Integer.valueOf(pageNumber),Integer.valueOf(pageSize));		
		return repository.findAll(page).getContent();
	
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


	


}
