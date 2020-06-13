package com.kiran.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class SimplePropertiesController {

	@Value("${my.greeting:default value}")
	private String greeting;
	
	@Value("${app.desc}")
	private String myAppDesc;
	
	@Value("${my.list.values}")
	private List<String> myList;
	
	@Value("#{${my.switches}}")
	private Map<String,String> mySwitches;
	
	@Autowired
	private DBSettings dbSettings;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/greeting")
	public String greeting() {
		return greeting +", "+myAppDesc+" , List : "+myList+" , map : "+mySwitches;
	}
	
	@GetMapping("/config")
	public String getConfig() {
		return dbSettings.getConnection()+" , "+dbSettings.getHost()+" , "+dbSettings.getPort();
	}
	
	@GetMapping("/env")
	public String getEnv() {
		return environment.toString();
	}
}
