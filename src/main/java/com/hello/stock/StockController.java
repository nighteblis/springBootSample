package com.hello.stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("stock")
public class StockController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StockServiceImpl stockService;
	
	
	@GetMapping("{id}")
	public ResponseEntity<Stock> getStockById(@PathVariable("id") Integer id)
	
	{
		Stock stock = stockService.getStockById(id);
		logger.info("=========stock/id");
		return new ResponseEntity<>(stock, HttpStatus.OK);
		
	}
	
	@GetMapping("{id}/{stockName}")
	public ResponseEntity<Stock> getStockById(@PathVariable("id") Integer id , @PathVariable("stockName") String stockName  )
	
	{
		
		Stock stock= stockService.updateStockById(id, stockName);
		return new ResponseEntity<>(stock, HttpStatus.OK);
	}	
	
	
	
	

}
