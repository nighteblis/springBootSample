package com.hello.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hello.common.utils.MyBeanUtils;
import com.hello.model.Stock;
import com.hello.model.StockHisData;
import com.hello.repository.BaseRepository;
import com.hello.repository.stock.StockRepository2;
import com.hello.repository.stockHisData.StockHisDataRepository;

public class StockHisDataServiceImpl extends BaseServiceImpl<StockHisData, StockHisDataRepository>{

	@Override
	@Autowired
	protected void setRepository(StockHisDataRepository repository) {
		// TODO Auto-generated method stub
		
		super.repository = repository;
		
	}




	
	
	

}
