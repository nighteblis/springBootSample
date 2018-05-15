package com.hello.stockHisData;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.hello.stock.Stock;

import lombok.Data;


@Entity
@Table(name="stockHisData")
@Data
public class StockHisData {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
        private Integer id;  

        private String stockId;
	

	   private String stockName;
	
	   @Version
	   private Integer version;

	
	
}
