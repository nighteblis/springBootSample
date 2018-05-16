package com.hello.common.exception;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.hello.model.BaseModel;

public class Assert {

	    public static void notNull(Object obj, String paras) {
	        if (obj == null) {  
	            throw exception("{} Could not be null", paras) ;
	        }
	    }

	    public static void notBlank(String str, String paras) {
	        if (str == null || StringUtils.trimWhitespace(str).equals("")) {
	            throw exception("{} is blank(null)", paras);
	        }
	    }

	    public static void notBlank(Enum<?> str, String paras) {
	        if (str == null) {
	            throw exception("{} Could not be null", paras);
	        }
	    }

	    public static void notEmpty(Collection<?> coll, String paras) {
	        if (CollectionUtils.isEmpty(coll)) {
	            throw exception("{} Could not be null", paras);
	        }
	    }
	    
	    public static boolean isBlank(String str) {
	    	 if (str == null || StringUtils.trimWhitespace(str).equals(""))
	    		 return true;
	    	 
	    	 return false;
	    	
	    }

	    public static void validStringLength(String str, int max, int min, boolean canEmpty, String paras) {
	        if (!canEmpty && isBlank(str)) {
	            throw exception("str is blank", paras);
	        } else if (!isBlank(str)) {
	            int length = str.length();
	            if (max < length || min > length) {
	                throw exception("{} length not valid", paras);
	            }
	        }
	    }

	    public static void isTrue(boolean flag, String paras) {
	        if (!flag) {
	            throw exception("", paras);
	        }
	    }

	    public static void notExist(BaseModel model, String paras) {
	        if (model == null) {
	            throw exception("{} is null", paras) ;
	        }
	    }

	    public static void notExist(Object model, String paras) {
	        if (model == null) {
	            throw exception("", paras);
	        }
	    }

	    public static void exist(BaseModel model, String paras) {
	        if (model != null) {
	            throw exception("", paras);
	        }
	    }

	
	    
	    public static ServiceException exception(String error , String ...params) {
	    	
	    	return new ServiceException( error , params);
	    	
	    }
	    public static ServiceException exception(String error ) {
	    	
	    	return new ServiceException( error );
	    	
	    }

}
