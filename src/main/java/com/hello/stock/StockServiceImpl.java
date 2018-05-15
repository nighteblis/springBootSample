package com.hello.stock;


import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StockServiceImpl implements StockService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StockRepository stockDao ;
	
	private  StockRepository2 repository ;
	
	
	@Autowired
	public void setRepository( StockRepository2 repository) {
		
		this.repository = repository;
	}
	
	public Stock getStockById(int id)
	{
		Stock obj = stockDao.getStockById(id);		
		
		return obj;
	}
	
	public List<Stock> getStocks(){
		
		List<Stock> stocks = new ArrayList<Stock>();		
		repository.findAll().forEach(stock -> {stocks.add((Stock) stock);} );			
		return stocks;
	}
	
	public Stock updateStockById(int id , Stock stock)
	{
		
		Stock originalStock = stockDao.getStockById(id);		
		BeanUtils.copyProperties(stock, originalStock,getNullPropertyNames(stock));		
		logger.info(stock.toString());
		logger.info(originalStock.toString());		
		
		repository.save(originalStock);
		//stockDao.updateStock(originalStock);	
		
		return originalStock;
		
	}
	
	
	public static String[] getNullPropertyNames(Object source) {
	    final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
	    	    
	    return Stream.of(wrappedSource.getPropertyDescriptors())
	            .map(FeatureDescriptor::getName)
	            .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null || propertyName.equalsIgnoreCase("id") )
	            .toArray(String[]::new);
	}

}
