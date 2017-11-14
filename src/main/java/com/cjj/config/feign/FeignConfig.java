package com.cjj.config.feign;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Request;
import feign.Retryer;

@Configuration
public class FeignConfig {

	@Bean
	public Retryer feignRetryer(){
		return new Retryer.Default(100,TimeUnit.SECONDS.toMillis(1),5); 
	}
	
	@Bean
    Request.Options feignOptions() {
        return new Request.Options(10 * 1000,60 * 1000);
    }
}
