package com.hello.stock;

import javax.persistence.Entity;

import javax.persistence.Table;
import com.hello.BaseModel;

import lombok.Data;




@Entity
@Table(name="stock")
@Data
public class Stock extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	

        private String stockId;
	

	   private String stockName;
	
	
}
