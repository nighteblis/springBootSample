package com.hello.common.utils;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class MyBeanUtils extends BeanUtils{
	
	
	

	
	public static String[] getNullPropertyNames(Object source) {
	    final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
	    	    
	    return Stream.of(wrappedSource.getPropertyDescriptors())
	            .map(FeatureDescriptor::getName)
	            .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null || propertyName.equalsIgnoreCase("id") )
	            .toArray(String[]::new);
	}
	
}
