package com.hello.model;

import javax.persistence.Entity;

import javax.persistence.Table;

import lombok.Data;




@Entity
@Table(name="stock")
@Data
public class Stock extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	

        private int stockId;
	

	   private String stockName;
	
	
}
