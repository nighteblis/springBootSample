package com.hello.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;


@Entity
@Table(name="stockHisData")
@Data
public class StockHisData extends BaseModel {


        private int stockId;
	
	   private String stockName;
	   
	   private int year;
	   
	   private Date date;
	   
	   private float initvalue;
	   	   
	   private float endvalue;
	   
	   private float marginamount;
	   private float marginpercentage;
	   private float low;
	   private float high;
	   private float dealamount;
	   private float dealvalue;
	   private float dealpercentage;


	
	
}
