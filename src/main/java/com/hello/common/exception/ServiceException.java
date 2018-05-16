package com.hello.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ServiceException extends RuntimeException{

    private static Logger logger = LoggerFactory.getLogger(ServiceException.class);

	
    private String code;

    private String message;
    
    
    public ServiceException(String errorCode) {
        this(errorCode, new String[0]);
    }

    public ServiceException(String errorCode, String... paras) {
        String message = "";
        try {
            message = String.format(errorCode, paras);
        } catch (Exception e) {
            logger.error("exception message format error : msg {}", errorCode, e);
        }
        this.message = message;
        this.code = errorCode;
    }
	
}
