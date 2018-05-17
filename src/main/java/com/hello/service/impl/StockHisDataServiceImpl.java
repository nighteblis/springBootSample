package com.hello.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hello.model.StockHisData;
import com.hello.repository.stockHisData.StockHisDataRepository;


@Service
public class StockHisDataServiceImpl extends BaseServiceImpl<StockHisData, StockHisDataRepository>{

	@Override
	@Autowired
	protected void setRepository(StockHisDataRepository repository) {
		// TODO Auto-generated method stub
		
		super.repository = repository;
		
	}




	
	
	

}
