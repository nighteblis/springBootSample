package com.hello.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.model.Stock;
import com.hello.service.impl.StockServiceImpl;

@Controller
@RequestMapping("/stock")
public class StockController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StockServiceImpl stockService;
	


	@GetMapping("")
	@ResponseBody
	public ResponseEntity<List<Stock>> getStocks(@RequestParam(value="page",required=false) String page , @RequestParam(value="size",required=false) String size)
	
	{
		
			return new ResponseEntity<>(stockService.getStocks(page,size), HttpStatus.OK);		
		
	}
	
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Stock> getStockById(@PathVariable("id") Long id)
	
	{
		Stock stock = stockService.getStockById(id);
		logger.info("=========stock/id");
		return new ResponseEntity<>(stock, HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Stock> updateStockById(@PathVariable("id") Long id,@RequestBody Stock stock  )
	
	{
		logger.info(stock.toString());		
		stock = stockService.updateStockById(id, stock);		
		return new ResponseEntity<>(stock, HttpStatus.OK);
		
	}	
	
	
	
	

}
