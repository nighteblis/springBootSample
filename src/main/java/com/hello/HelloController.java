package com.hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


@RestController
public class HelloController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private ApplicationContext appContext;

    @RequestMapping("/")
    public String index() {
    	
    	System.out.println(appContext.getApplicationName());
    	System.out.println(appContext.getBeanDefinitionCount());
        String[] beanNames = appContext.getBeanNamesForAnnotation(Autowired.class);
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
          System.out.println(beanName);
        }
        
        return "Greetings from Spring Boot!";
    }

}